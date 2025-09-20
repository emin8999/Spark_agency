// assets/js/headerFooter.js
import { getContent, getT, setLocale, onContentChange } from "./i18n.js";

function icon(name) {
  const map = {
    instagram:
      '<svg width="18" height="18" viewBox="0 0 24 24" fill="currentColor"><path d="M7 2h10a5 5 0 0 1 5 5v10a5 5 0 0 1-5 5H7a5 5 0 0 1-5-5V7a5 5 0 0 1 5-5zm0 2a3 3 0 0 0-3 3v10a3 3 0 0 0 3 3h10a3 3 0 0 0 3-3V7a3 3 0 0 0-3-3H7zm12 2a1 1 0 1 1 0 2 1 1 0 0 1 0-2zM12 7a5 5 0 1 1 0 10 5 5 0 0 1 0-10z"/></svg>',
    facebook:
      '<svg width="18" height="18" viewBox="0 0 24 24" fill="currentColor"><path d="M13 22V12h3l1-4h-4V6c0-1.084.916-2 2-2h2V0h-3a5 5 0 0 0-5 5v3H6v4h3v10h4z"/></svg>',
    tiktok:
      '<svg width="18" height="18" viewBox="0 0 24 24" fill="currentColor"><path d="M16 3c1 2 2 3 4 3v4c-2 0-4-1-4-1v6a6 6 0 1 1-6-6h1v4h-1a2 2 0 1 0 2 2V3h4z"/></svg>',
  };
  return map[name] || "";
}

function burgerIcon() {
  return `
    <svg width="22" height="22" viewBox="0 0 24 24" fill="none" aria-hidden="true">
      <path d="M4 6h16M4 12h16M4 18h16" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
    </svg>`;
}
function closeIcon() {
  return `
    <svg width="22" height="22" viewBox="0 0 24 24" fill="none" aria-hidden="true">
      <path d="M6 6l12 12M18 6L6 18" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
    </svg>`;
}

export function renderHeaderFooter() {
  const rootHeader = document.getElementById("site-header");
  const rootFooter = document.getElementById("site-footer");
  const content = getContent();
  const t = getT();

  if (rootHeader) {
    const hasLogo = !!content.site.logoSrc;

    // Desktop nav (visible ≥ 901px), burger visible on mobile
    rootHeader.innerHTML = `
    <header class="header">
      <div class="header-inner container">
        <a class="brand" href="index.html" style="display:flex;align-items:center;gap:10px;">
          ${
            hasLogo
              ? `<img src="${content.site.logoSrc}" alt="logo" style="width:36px;height:36px;object-fit:cover;border-radius:10px;border:1px solid var(--line)">`
              : ""
          }
          <span>${content.site.brand}</span>
        </a>

        <nav class="nav desktop-nav">
          ${content.site.nav
            .map(
              (n) => `<a href="${n.href}" data-i18n="${n.key}">${t(n.key)}</a>`
            )
            .join("")}
        </nav>

        <div class="lang-switch desktop-nav">
          <button data-lang="ru" class="lang-btn">RU</button>
          <button data-lang="az" class="lang-btn">AZ</button>
        </div>

        <button class="burger-btn" aria-label="Открыть меню" aria-controls="mobile-drawer" aria-expanded="false">
          ${burgerIcon()}
        </button>
      </div>
    </header>

    <div class="drawer-backdrop" data-drawer-backdrop></div>
    <aside id="mobile-drawer" class="mobile-drawer" aria-hidden="true">
      <div class="drawer-header">
        <div class="brand-mini">
          ${
            hasLogo
              ? `<img src="${content.site.logoSrc}" alt="logo" style="width:28px;height:28px;object-fit:cover;border-radius:8px;border:1px solid var(--line)">`
              : ""
          }
          <span>${content.site.brand}</span>
        </div>
        <button class="drawer-close" aria-label="Закрыть меню">${closeIcon()}</button>
      </div>
      <nav class="drawer-nav">
        ${content.site.nav
          .map(
            (n) =>
              `<a href="${n.href}" data-i18n="${n.key}" class="drawer-link">${t(
                n.key
              )}</a>`
          )
          .join("")}
      </nav>
      <div class="drawer-lang">
        <button data-lang="ru" class="lang-btn">RU</button>
        <button data-lang="az" class="lang-btn">AZ</button>
      </div>
      <div class="drawer-socials">
        ${content.site.socials
          .map(
            (s) =>
              `<a href="${s.url}" target="_blank" rel="noopener">${icon(
                s.icon
              )}</a>`
          )
          .join("")}
      </div>
    </aside>
    `;

    // Bind language switchers
    rootHeader.querySelectorAll(".lang-btn").forEach((btn) => {
      btn.addEventListener("click", () => {
        setLocale(btn.dataset.lang);
      });
    });

    // Drawer handlers
    const drawer = document.getElementById("mobile-drawer");
    const backdrop = rootHeader.parentElement.querySelector(
      "[data-drawer-backdrop]"
    );
    const burgerBtn = rootHeader.querySelector(".burger-btn");
    const closeBtn = rootHeader.parentElement.querySelector(".drawer-close");

    function openDrawer() {
      drawer.classList.add("open");
      backdrop.classList.add("show");
      drawer.setAttribute("aria-hidden", "false");
      burgerBtn.setAttribute("aria-expanded", "true");
      document.body.classList.add("no-scroll");
    }
    function closeDrawer() {
      drawer.classList.remove("open");
      backdrop.classList.remove("show");
      drawer.setAttribute("aria-hidden", "true");
      burgerBtn.setAttribute("aria-expanded", "false");
      document.body.classList.remove("no-scroll");
    }

    burgerBtn?.addEventListener("click", openDrawer);
    closeBtn?.addEventListener("click", closeDrawer);
    backdrop?.addEventListener("click", closeDrawer);

    // Close on ESC
    document.addEventListener("keydown", (e) => {
      if (e.key === "Escape" && drawer.classList.contains("open"))
        closeDrawer();
    });

    // Close after clicking link
    drawer.querySelectorAll("a.drawer-link").forEach((a) => {
      a.addEventListener("click", closeDrawer);
    });
  }

  if (rootFooter) {
    rootFooter.innerHTML = `
    <footer class="footer">
      <div class="footer-inner container">
        <div class="footer-note" data-i18n="footer_note">${t(
          "footer_note"
        )}</div>
        <nav class="nav">
          ${content.site.socials
            .map(
              (s) =>
                `<a href="${s.url}" target="_blank" rel="noopener">${icon(
                  s.icon
                )}</a>`
            )
            .join("")}
        </nav>
      </div>
    </footer>`;
  }
}

document.addEventListener("DOMContentLoaded", () => {
  renderHeaderFooter();
  onContentChange(() => {
    renderHeaderFooter();
  });
});
