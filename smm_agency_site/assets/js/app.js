// assets/js/app.js
import { getContent, onContentChange, updateContent } from "./i18n.js";

/* ---------- Ensure "about" exists with sensible defaults ---------- */
function ensureAbout() {
  const c = getContent();
  if (!c.about) {
    updateContent((cc) => {
      cc.about = {
        hero: {
          title: {
            ru: "О нас — креатив & рост",
            az: "Haqqımızda — kreativlik & artım",
          },
          subtitle: {
            ru: "Мы соединяем стратегию, дизайн и перформанс.",
            az: "Strategiya, dizayn və performansı birləşdiririk.",
          },
          imageSrc: "",
        },
        stats: [
          { label: { ru: "Годы опыта", az: "İllik təcrübə" }, value: "5+" },
          { label: { ru: "Проектов", az: "Layihə" }, value: "120+" },
        ],
        values: [
          { ru: "Клиентская одержимость", az: "Müştəriyə fokus" },
          { ru: "Смелый креатив", az: "Cəsarətli kreativ" },
        ],
        timeline: [
          {
            year: "2023",
            text: { ru: "Открыли агентство", az: "Agentliyi açdıq" },
          },
          {
            year: "2024",
            text: { ru: "50+ успешных кейсов", az: "50+ uğurlu layihə" },
          },
        ],
        team: [
          {
            name: "Team Lead",
            role: { ru: "Креатив-директор", az: "Kreativ direktor" },
            avatarSrc: "",
          },
        ],
      };
    });
  }
}

/* ---------- Core sections ---------- */
function renderServices() {
  const root = document.getElementById("services-list");
  if (!root) return;
  const c = getContent();
  root.innerHTML = c.services
    .map((s) => {
      const iconHtml = s.iconSrc
        ? `<img src="${s.iconSrc}" alt="" style="width:28px;height:28px;object-fit:cover;border-radius:8px;border:1px solid var(--line)">`
        : `<div class="icon">${s.icon || "✨"}</div>`;
      return `
      <div class="item card">
        <div class="icon" style="display:flex;align-items:center;justify-content:center;width:36px;height:36px;">
          ${iconHtml}
        </div>
        <div>
          <div class="title">${s.title?.[c.locale] || ""}</div>
          <div class="desc" style="color:var(--fg)">${
            s.desc?.[c.locale] || ""
          }</div>
        </div>
      </div>`;
    })
    .join("");
}

function renderPackages() {
  const root = document.getElementById("packages-list");
  if (!root) return;
  const c = getContent();
  root.innerHTML = c.packages
    .map(
      (p) => `
    <div class="item card">
      <div class="badge">${p.name?.[c.locale] || ""}</div>
      <div style="font-size:28px; font-weight:800;">${p.price} ${
        p.currency || ""
      }</div>
      <div style="color:var(--fg)">${(p.features?.[c.locale] || [])
        .map((f) => `• ${f}`)
        .join("<br>")}</div>
    </div>
  `
    )
    .join("");
}

function renderGallery() {
  const root = document.getElementById("gallery-list");
  if (!root) return;
  const c = getContent();
  root.innerHTML = c.gallery
    .map(
      (g) => `
    <div class="item">
      ${
        g.type === "video"
          ? `<video src="${g.src}" controls playsinline></video>`
          : `<img src="${g.src}" alt="${g.alt || ""}">`
      }
    </div>
  `
    )
    .join("");
}

function renderFAQ() {
  const root = document.getElementById("faq-list");
  if (!root) return;
  const c = getContent();
  root.innerHTML = c.faq
    .map(
      (x) => `
    <div class="qa">
      <strong>${x.q?.[c.locale] || ""}</strong>
      <div style="color:var(--muted)">${x.a?.[c.locale] || ""}</div>
    </div>
  `
    )
    .join("");
}

function renderHero() {
  const c = getContent();
  const h1 = document.querySelector('[data-i18n="hero_title"]');
  const p = document.querySelector('[data-i18n="hero_subtitle"]');
  if (h1) h1.textContent = c.locales?.[c.locale]?.hero_title || "—";
  if (p) p.textContent = c.locales?.[c.locale]?.hero_subtitle || "—";
}

/* ---------- About page sections ---------- */
function renderAboutHero() {
  const root = document.getElementById("about-hero");
  if (!root) return;
  ensureAbout();
  const c = getContent();
  const a = c.about;
  const L = c.locale;
  const img = a.hero?.imageSrc
    ? `<img src="${a.hero.imageSrc}" alt="" style="width:100%;max-height:320px;object-fit:cover;border-radius:12px;margin-bottom:12px;border:1px solid var(--line)">`
    : "";
  root.innerHTML = `
    ${img}
    <h2 style="margin:6px 0;">${a.hero?.title?.[L] || ""}</h2>
    <p style="color:var(--muted);margin:0;">${a.hero?.subtitle?.[L] || ""}</p>
  `;
}

function renderAboutStats() {
  const root = document.getElementById("about-stats");
  if (!root) return;
  const c = getContent();
  const a = c.about;
  const L = c.locale;
  root.innerHTML = (a.stats || [])
    .map(
      (s) => `
    <div class="card" style="grid-column: span 3; text-align:center;">
      <div style="font-size:28px;font-weight:800;">${s.value}</div>
      <div style="color:var(--muted)">${s.label?.[L] || ""}</div>
    </div>
  `
    )
    .join("");
}

function renderAboutValues() {
  const root = document.getElementById("about-values");
  if (!root) return;
  const c = getContent();
  const a = c.about;
  const L = c.locale;
  root.innerHTML = (a.values || [])
    .map(
      (v) => `
    <div class="card" style="grid-column: span 6;">
      <div style="font-weight:700;">${v[L] || ""}</div>
      <div style="color:var(--muted);">—</div>
    </div>
  `
    )
    .join("");
}

function renderAboutTimeline() {
  const root = document.getElementById("about-timeline");
  if (!root) return;
  const c = getContent();
  const a = c.about;
  const L = c.locale;
  root.innerHTML = `
    <div style="position:relative;padding-left:20px;">
      ${(a.timeline || [])
        .map(
          (t) => `
        <div style="display:flex;gap:12px;align-items:flex-start;margin:10px 0;">
          <div style="width:8px;height:8px;border-radius:999px;background:var(--acc);margin-top:8px;"></div>
          <div>
            <div class="badge">${t.year}</div>
            <div>${t.text?.[L] || ""}</div>
          </div>
        </div>
      `
        )
        .join("")}
    </div>
  `;
}

function renderAboutTeam() {
  const root = document.getElementById("about-team");
  if (!root) return;
  const c = getContent();
  const a = c.about;
  const L = c.locale;
  root.innerHTML = (a.team || [])
    .map(
      (m) => `
    <div class="card" style="grid-column: span 4; display:flex; gap:12px; align-items:center;">
      ${
        m.avatarSrc
          ? `<img src="${m.avatarSrc}" alt="" style="width:56px;height:56px;object-fit:cover;border-radius:12px;border:1px solid var(--line)">`
          : `<div class="badge">No photo</div>`
      }
      <div>
        <div style="font-weight:700">${m.name || ""}</div>
        <div style="color:var(--muted)">${m.role?.[L] || ""}</div>
      </div>
    </div>
  `
    )
    .join("");
}

/* ---------- Orchestrator ---------- */
export function renderAll() {
  renderHero();
  renderServices();
  renderPackages();
  renderGallery();
  renderFAQ();
  renderAboutHero();
  renderAboutStats();
  renderAboutValues();
  renderAboutTimeline();
  renderAboutTeam();
}

document.addEventListener("DOMContentLoaded", () => {
  ensureAbout();
  renderAll();
  onContentChange(() => {
    renderAll();
  });
});
