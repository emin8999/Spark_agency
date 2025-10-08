// assets/js/app.js
import { getContent, onContentChange, updateContent } from "./i18n.js";
import { syncAllFromAPI } from "./apiClient.js";

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
    .map((s, index) => {
      const iconHtml = s.iconSrc
        ? `<img src="${s.iconSrc}" alt="" style="width:32px;height:32px;object-fit:cover;border-radius:8px;border:1px solid var(--border-primary)">`
        : `<div class="icon">${s.icon || "✨"}</div>`;
      return `
      <div class="item card" style="animation-delay: ${index * 0.1}s;">
        <div class="icon" style="display:flex;align-items:center;justify-content:center;width:48px;height:48px;">
          ${iconHtml}
        </div>
        <div>
          <div class="title">${s.title?.[c.locale] || ""}</div>
          <div class="desc">${s.desc?.[c.locale] || ""}</div>
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
      (p, index) => {
        const isFeatured = index === 1; // Make the second package featured
        const features = (p.features?.[c.locale] || []);
        return `
    <div class="item card ${isFeatured ? 'featured' : ''}" style="animation-delay: ${index * 0.1}s;">
      <div class="badge">${p.name?.[c.locale] || ""}</div>
      <div class="price-container">
        <span class="price">${p.price}</span>
        <span class="currency">${p.currency || ""}</span>
      </div>
      <ul class="features">
        ${features.map(feature => `<li>${feature}</li>`).join('')}
      </ul>
      <div class="cta">
        <a href="contact.html" class="btn ${isFeatured ? 'pulse' : ''}">${c.locale === 'az' ? 'Seç' : 'Выбрать'}</a>
      </div>
    </div>
  `;
      }
    )
    .join("");
}

function renderGallery() {
  const root = document.getElementById("gallery-list");
  if (!root) return;
  const c = getContent();
  root.innerHTML = c.gallery
    .map(
      (g, index) => `
    <div class="item" style="animation-delay: ${index * 0.1}s;">
      ${
        g.type === "video"
          ? `<video src="${g.src}" controls playsinline style="width:100%;height:250px;object-fit:cover;"></video>`
          : `<img src="${g.src}" alt="${g.alt || ""}" style="width:100%;height:250px;object-fit:cover;">`
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
      (x, index) => `
    <div class="qa" style="animation-delay: ${index * 0.1}s;">
      <strong>${x.q?.[c.locale] || ""}</strong>
      <div>${x.a?.[c.locale] || ""}</div>
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
      (s, index) => `
    <div class="card" style="grid-column: span 3; text-align:center; animation-delay: ${index * 0.1}s;">
      <div style="font-size:2.5rem;font-weight:800;background:var(--gradient-primary);-webkit-background-clip:text;-webkit-text-fill-color:transparent;background-clip:text;">${s.value}</div>
      <div style="color:var(--text-secondary);font-weight:500;">${s.label?.[L] || ""}</div>
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
      (v, index) => `
    <div class="card" style="grid-column: span 6; animation-delay: ${index * 0.1}s;">
      <div style="font-weight:700;color:var(--text-primary);font-size:1.125rem;">${v[L] || ""}</div>
      <div style="color:var(--text-secondary);margin-top:0.5rem;">—</div>
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
      (m, index) => `
    <div class="card team-card" data-team-card style="animation-delay: ${index * 0.1}s;">
      ${
        m.avatarSrc
          ? `<img src="${m.avatarSrc}" alt="" class="team-card__avatar" loading="lazy">`
          : `<div class="team-card__placeholder">${(m.name || "T")[0].toUpperCase()}</div>`
      }
      <div class="team-card__body">
        <div class="team-card__name">${m.name || ""}</div>
        <div class="team-card__role">${m.role?.[L] || ""}</div>
      </div>
    </div>
  `
    )
    .join("");

  const cards = root.querySelectorAll("[data-team-card]");
  cards.forEach((card) => {
    card.addEventListener("click", () => {
      const isAlreadyExpanded = card.classList.contains("is-expanded");
      cards.forEach((c) => c.classList.remove("is-expanded"));
      if (!isAlreadyExpanded) card.classList.add("is-expanded");
    });
  });
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

let syncing = false;
async function refreshFromAPI({ silent = false } = {}) {
  if (syncing) return;
  syncing = true;
  try {
    await syncAllFromAPI();
  } catch (err) {
    if (!silent) console.error("Failed to sync content", err);
  } finally {
    renderAll();
    syncing = false;
  }
}

document.addEventListener("DOMContentLoaded", async () => {
  ensureAbout();
  await refreshFromAPI({ silent: true });
  onContentChange(() => {
    renderAll();
  });
  
  // Add intersection observer for animations
  const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
  };
  
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.classList.add('visible');
      }
    });
  }, observerOptions);
  
  // Observe all cards and sections
  document.querySelectorAll('.card, .section-title, .hero').forEach(el => {
    el.classList.add('fade-in');
    observer.observe(el);
  });
  
  // Add page transition effect
  document.body.classList.add('page-transition');
  setTimeout(() => {
    document.body.classList.add('loaded');
  }, 100);
});

document.addEventListener("visibilitychange", () => {
  if (!document.hidden) refreshFromAPI({ silent: true });
});
window.addEventListener("focus", () => refreshFromAPI({ silent: true }));
