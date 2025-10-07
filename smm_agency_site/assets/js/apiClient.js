// Minimal API client to sync backend data into the existing
// local content model used by the static site.

import { updateContent, getContent } from "./i18n.js";

const BASE = window.API_BASE_URL || "http://sparkagencyllc.com/sparkagency"; // e.g., "http://localhost:8080"

async function safeJson(resp) {
  if (!resp.ok) {
    const message = await resp.text().catch(() => "");
    throw new Error(`${resp.status} ${message}`.trim());
  }
  if (resp.status === 204) return null;
  const text = await resp.text();
  if (!text) return null;
  try {
    return JSON.parse(text);
  } catch (e) {
    console.warn("Failed to parse JSON response", e);
    return null;
  }
}

async function jsonRequest(path, { method = "GET", body, headers = {} } = {}) {
  const opts = {
    method,
    headers: { ...headers },
  };
  if (body !== undefined) {
    opts.body = JSON.stringify(body);
    opts.headers["Content-Type"] = "application/json";
  }
  const resp = await fetch(`${BASE}${path}`, opts);
  return safeJson(resp);
}

async function uploadFile(path, file, fieldName = "file", extraFields = {}) {
  const form = new FormData();
  form.append(fieldName, file);
  Object.entries(extraFields).forEach(([key, value]) => {
    if (value !== undefined && value !== null) form.append(key, value);
  });
  const resp = await fetch(`${BASE}${path}`, {
    method: "POST",
    body: form,
  });
  return safeJson(resp);
}

async function getServices() {
  try {
    const data = await safeJson(await fetch(`${BASE}/api/services/get`));
    return (data || []).map((s) => ({
      id: s.id,
      icon: s.icon || "",
      iconSrc: s.iconSrc || "",
      title: { ru: s.titleRu || "", az: s.titleAz || "" },
      desc: { ru: s.descriptionRu || "", az: s.descriptionAz || "" },
    }));
  } catch (_) {
    return null;
  }
}

async function getPackages() {
  try {
    const data = await safeJson(await fetch(`${BASE}/api/packages/get`));
    return (data || []).map((p) => ({
      id: p.id,
      name: { ru: p.nameRu || "", az: p.nameAz || "" },
      price: typeof p.price === "number" ? p.price : Number(p.price || 0),
      currency: p.currency || "",
      features: { ru: p.featuresRu || [], az: p.featuresAz || [] },
    }));
  } catch (_) {
    return null;
  }
}

async function getFaq() {
  try {
    const data = await safeJson(await fetch(`${BASE}/api/faq/get`));
    return (data || []).map((f) => ({
      id: f.id,
      q: { ru: f.questionRu || "", az: f.questionAz || "" },
      a: { ru: f.answerRu || "", az: f.answerAz || "" },
    }));
  } catch (_) {
    return null;
  }
}

async function getSite() {
  try {
    const s = await safeJson(await fetch(`${BASE}/api/site/get`));
    return {
      id: s?.id,
      brand: s.brandName || "",
      logoSrc: s.logoSrc || "",
      hero: {
        title: { ru: s.heroTitleRu || "", az: s.heroTitleAz || "" },
        subtitle: { ru: s.heroSubtitleRu || "", az: s.heroSubtitleAz || "" },
      },
    };
  } catch (_) {
    return null;
  }
}

async function getGallery() {
  try {
    const data = await safeJson(await fetch(`${BASE}/api/gallery/get`));
    return (data || []).map((g) => ({
      id: g.id,
      type: g.type || "image",
      src: g.src || "",
      alt: g.alt || "",
    }));
  } catch (_) {
    return null;
  }
}

async function getAbout() {
  try {
    const a = await safeJson(await fetch(`${BASE}/api/about/get`));
    return {
      hero: {
        id: a.id,
        title: { ru: a.heroTitleRu || "", az: a.heroTitleAz || "" },
        subtitle: { ru: a.heroSubtitleRu || "", az: a.heroSubtitleAz || "" },
        imageSrc: a.heroImageSrc || "",
      },
      stats: (a.stats || []).map((s) => ({
        id: s.id,
        label: { ru: s.labelRu || "", az: s.labelAz || "" },
        value: s.value || "",
      })),
      values: (a.values || []).map((v) => ({
        id: v.id,
        ru: v.valueRu || "",
        az: v.valueAz || "",
      })),
      timeline: (a.timeline || []).map((t) => ({
        id: t.id,
        year: t.year || "",
        text: { ru: t.textRu || "", az: t.textAz || "" },
      })),
      team: (a.team || []).map((m) => ({
        id: m.id,
        name: m.name || "",
        role: { ru: m.roleRu || "", az: m.roleAz || "" },
        avatarSrc: m.avatarSrc || "",
      })),
    };
  } catch (_) {
    return null;
  }
}

export async function syncAllFromAPI() {
  const [site, services, pkgs, faq, gallery, about] = await Promise.all([
    getSite(),
    getServices(),
    getPackages(),
    getFaq(),
    getGallery(),
    getAbout(),
  ]);

  // Merge each section into local content only if API responded
  updateContent((c) => {
    if (site) {
      c.site = c.site || {};
      c.site.id = site.id;
      c.site.brand = site.brand;
      c.site.logoSrc = site.logoSrc;
      // hero text is stored inside locales
      c.locales = c.locales || { ru: {}, az: {} };
      c.locales.ru.hero_title = site.hero.title.ru;
      c.locales.az.hero_title = site.hero.title.az;
      c.locales.ru.hero_subtitle = site.hero.subtitle.ru;
      c.locales.az.hero_subtitle = site.hero.subtitle.az;
    }

    if (Array.isArray(services)) c.services = services;
    if (Array.isArray(pkgs)) c.packages = pkgs;
    if (Array.isArray(faq)) c.faq = faq;
    if (Array.isArray(gallery)) c.gallery = gallery;

    if (about) {
      const current = getContent();
      const existingAbout = current.about || {};
      c.about = {
        ...existingAbout,
        hero: about.hero,
        stats: about.stats,
        values: about.values,
        timeline: about.timeline,
        team: about.team,
      };
    }
  });
}

/* ---------- Services API ---------- */
export const ServicesAPI = {
  list: () => jsonRequest("/api/services/get"),
  create: (payload) => jsonRequest("/api/services/create", { method: "POST", body: payload }),
  update: (id, payload) => jsonRequest(`/api/services/update/${id}`, { method: "PUT", body: payload }),
  delete: (id) => jsonRequest(`/api/services/delete/${id}`, { method: "DELETE" }),
  uploadIcon: (id, file) => uploadFile(`/api/services/upload/${id}/icon`, file),
  uploadIconBase64: (id, base64Data) =>
    jsonRequest(`/api/services/upload/${id}/icon/base64`, {
      method: "POST",
      body: { base64Data },
    }),
};

/* ---------- Packages API ---------- */
export const PackagesAPI = {
  list: () => jsonRequest("/api/packages/get"),
  create: (payload) => jsonRequest("/api/packages/create", { method: "POST", body: payload }),
  update: (id, payload) => jsonRequest(`/api/packages/update/${id}`, { method: "PUT", body: payload }),
  delete: (id) => jsonRequest(`/api/packages/delete/${id}`, { method: "DELETE" }),
};

/* ---------- FAQ API ---------- */
export const FaqAPI = {
  list: () => jsonRequest("/api/faq/get"),
  create: (payload) => jsonRequest("/api/faq/create", { method: "POST", body: payload }),
  update: (id, payload) => jsonRequest(`/api/faq/update/${id}`, { method: "PUT", body: payload }),
  delete: (id) => jsonRequest(`/api/faq/delete/${id}`, { method: "DELETE" }),
};

/* ---------- Site (Quick edit) API ---------- */
export const SiteAPI = {
  get: () => jsonRequest("/api/site/get"),
  create: (payload) => jsonRequest("/api/site/create", { method: "POST", body: payload }),
  update: (payload) => jsonRequest("/api/site/update", { method: "PUT", body: payload }),
  uploadLogo: (file) => uploadFile("/api/site/logo", file),
};

/* ---------- Gallery API ---------- */
export const GalleryAPI = {
  list: () => jsonRequest("/api/gallery/get"),
  create: (payload) => jsonRequest("/api/gallery/create", { method: "POST", body: payload }),
  uploadImage: (file, { alt } = {}) =>
    uploadFile("/api/gallery/upload/image", file, "file", { alt }),
  uploadVideo: (file, { alt } = {}) =>
    uploadFile("/api/gallery/upload/video", file, "file", { alt }),
  delete: (id) => jsonRequest(`/api/gallery/delete/${id}`, { method: "DELETE" }),
};

/* ---------- About API ---------- */
export const AboutAPI = {
  get: () => jsonRequest("/api/about/get"),
  updateHero: (payload) => jsonRequest("/api/about/hero", { method: "PUT", body: payload }),
  uploadHeroImage: (base64Data) =>
    jsonRequest("/api/about/hero/image/base64", {
      method: "POST",
      body: { base64Data },
    }),
  // Stats
  createStat: (payload) => jsonRequest("/api/about/create/stats", { method: "POST", body: payload }),
  updateStat: (id, payload) => jsonRequest(`/api/about/update/stats/${id}`, { method: "PUT", body: payload }),
  deleteStat: (id) => jsonRequest(`/api/about/delete/stats/${id}`, { method: "DELETE" }),
  // Values
  createValue: (payload) => jsonRequest("/api/about/create/values", { method: "POST", body: payload }),
  updateValue: (id, payload) => jsonRequest(`/api/about/update/values/${id}`, { method: "PUT", body: payload }),
  deleteValue: (id) => jsonRequest(`/api/about/delete/values/${id}`, { method: "DELETE" }),
  // Timeline
  createTimeline: (payload) => jsonRequest("/api/about/create/timeline", { method: "POST", body: payload }),
  updateTimeline: (id, payload) => jsonRequest(`/api/about/update/timeline/${id}`, { method: "PUT", body: payload }),
  deleteTimeline: (id) => jsonRequest(`/api/about/delete/timeline/${id}`, { method: "DELETE" }),
  // Team
  createTeamMember: (payload) => jsonRequest("/api/about/create/team", { method: "POST", body: payload }),
  updateTeamMember: (id, payload) => jsonRequest(`/api/about/update/team/${id}`, { method: "PUT", body: payload }),
  deleteTeamMember: (id) => jsonRequest(`/api/about/delete/team/${id}`, { method: "DELETE" }),
  uploadTeamAvatar: (id, base64Data) =>
    jsonRequest(`/api/about/create/team/${id}/avatar/base64`, {
      method: "POST",
      body: { base64Data },
    }),
};
