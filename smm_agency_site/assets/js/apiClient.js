// Minimal API client to sync backend data into the existing
// local content model used by the static site.

import { updateContent, getContent } from "./i18n.js";

const BASE = window.API_BASE_URL || ""; // e.g., "http://localhost:8080"

async function safeJson(resp) {
  if (!resp.ok) throw new Error(`${resp.status}`);
  return resp.json();
}

async function getServices() {
  try {
    const data = await safeJson(await fetch(`${BASE}/api/services/get`));
    return (data || []).map((s) => ({
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
        title: { ru: a.heroTitleRu || "", az: a.heroTitleAz || "" },
        subtitle: { ru: a.heroSubtitleRu || "", az: a.heroSubtitleAz || "" },
        imageSrc: a.heroImageSrc || "",
      },
      stats: (a.stats || []).map((s) => ({
        label: { ru: s.labelRu || "", az: s.labelAz || "" },
        value: s.value || "",
      })),
      values: (a.values || []).map((v) => ({ ru: v.valueRu || "", az: v.valueAz || "" })),
      timeline: (a.timeline || []).map((t) => ({
        year: t.year || "",
        text: { ru: t.textRu || "", az: t.textAz || "" },
      })),
      team: (a.team || []).map((m) => ({
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

