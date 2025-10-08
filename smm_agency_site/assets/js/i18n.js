const defaultContent = JSON.parse(
  `{"site": {"brand": "Spark Agency MMC", "nav": [{"href": "index.html", "key": "home"}, {"href": "services.html", "key": "services"}, {"href": "cases.html", "key": "cases"}, {"href": "about.html", "key": "about"}, {"href": "contact.html", "key": "contact"}], "socials": [{"icon": "instagram", "url": "https://www.instagram.com/sparkagency.mmc"}, {"icon": "facebook", "url": "https://facebook.com/your_agency"}, {"icon": "tiktok", "url": "https://tiktok.com/@your_agency"}], "footer_note_key": "footer_note"}, "locale": "ru", "locales": {"ru": {"home": "Главная", "services": "Услуги", "cases": "Кейсы", "about": "О нас", "contact": "Контакты", "hero_title": "SMM-агентство полного цикла", "hero_subtitle": "Дизайн, креатив, стратегия и рост — всё в одном месте.", "cta_contact": "Связаться", "section_services_title": "Что мы делаем", "section_cases_title": "Наши кейсы", "section_about_title": "Кто мы", "section_contact_title": "Напишите нам", "packages_title": "Пакеты и цены", "faq_title": "FAQ — Частые вопросы", "about_values_title": "Наши ценности", "about_timeline_title": "История и достижения", "about_team_title": "Команда", "footer_note": "© 2025 Alievs Space MMC. Все права защищены.", "contact_form_name": "Ваше имя", "contact_form_email": "Ваш e‑mail", "contact_form_msg": "Ваше сообщение", "contact_form_send": "Отправить"}, "az": {"home": "Ana səhifə", "services": "Xidmətlər", "cases": "Layihələr", "about": "Haqqımızda", "contact": "Əlaqə", "hero_title": "Tam dövriyyəli SMM agentliyi", "hero_subtitle": "Dizayn, kreativ, strategiya və artım — hamısı bir yerdə.", "cta_contact": "Əlaqə", "section_services_title": "Nə edirik", "section_cases_title": "Layihələrimiz", "section_about_title": "Biz kimik", "section_contact_title": "Bizə yazın", "packages_title": "Paketlər və qiymətlər", "faq_title": "FAQ — Tez-tez verilən suallar", "about_values_title": "Dəyərlərimiz", "about_timeline_title": "Tarixçə və nailiyyətlər", "about_team_title": "Komanda", "footer_note": "© 2025 Spark Agency MMC. Bütün hüquqlar qorunur.", "contact_form_name": "Adınız", "contact_form_email": "E‑poçtunuz", "contact_form_msg": "Mesajınız", "contact_form_send": "Göndər"}}, "services": [{"icon": "🧠", "title": {"ru": "Стратегия", "az": "Strategiya"}, "desc": {"ru": "Аналитика, позиционирование, контент‑план.", "az": "Analitika, mövqeləndirmə, kontent planı."}}, {"icon": "🎨", "title": {"ru": "Дизайн", "az": "Dizayn"}, "desc": {"ru": "Премиальные визуалы для бренда и ленты.", "az": "Brend və lent üçün premium vizuallar."}}, {"icon": "📈", "title": {"ru": "Реклама", "az": "Reklam"}, "desc": {"ru": "Таргет Meta/Google, рост лидов и продаж.", "az": "Meta/Google hədəfləmə, lead və satış artımı."}}, {"icon": "🎬", "title": {"ru": "Продакшн", "az": "Prodakşn"}, "desc": {"ru": "Фото/видео, Reels, сторис, монтаж.", "az": "Foto/video, Reels, storilər, montaj."}}], "packages": [{"name": {"ru": "Базовый", "az": "Basic"}, "price": 49, "currency": "USD", "features": {"ru": ["Контент‑план", "10 постов/мес", "Stories шаблоны"], "az": ["Kontent planı", "Ayda 10 post", "Stories şablonları"]}}, {"name": {"ru": "Стандарт", "az": "Standard"}, "price": 79, "currency": "USD", "features": {"ru": ["Таргетинг", "15 постов/мес", "Дизайн ленты"], "az": ["Hədəfləmə", "Ayda 15 post", "Lent dizaynı"]}}, {"name": {"ru": "Премиум", "az": "Premium"}, "price": 129, "currency": "USD", "features": {"ru": ["Съёмка 1×/мес", "Рекламные креативы", "Отчёты"], "az": ["Aylıq 1 çəkiliş", "Reklam kreativləri", "Hesabatlar"]}}], "gallery": [{"type": "image", "src": "assets/img/sample1.jpg", "alt": "Case 1"}, {"type": "image", "src": "assets/img/sample2.jpg", "alt": "Case 2"}, {"type": "video", "src": "https://www.w3schools.com/html/mov_bbb.mp4", "alt": "Showreel"}], "faq": [{"q": {"ru": "Сколько занимает запуск?", "az": "Başlama nə qədər çəkir?"}, "a": {"ru": "Обычно 3–7 дней после согласования.", "az": "Adətən razılaşmadan sonra 3–7 gün."}}, {"q": {"ru": "Вы делаете сайты и e‑commerce?", "az": "Sayt və e‑commerce edirsiniz?"}, "a": {"ru": "Да, можем собрать витрину и подключить оплату.", "az": "Bəli, vitrin və ödəniş qoşuruq."}}]}`
);

let content = JSON.parse(JSON.stringify(defaultContent));

function saveContent() {
  listeners.forEach((fn) => fn(content));
}

export function getContent() {
  return content;
}
export function setContent(newContent) {
  content = newContent;
  saveContent();
}
export function updateContent(mutator) {
  const c = getContent();
  mutator(c);
  saveContent();
}

export function getLocale() {
  return content.locale || "ru";
}
export function setLocale(loc) {
  content.locale = loc;
  saveContent();
  refreshI18nText();
}

export function getT() {
  const loc = getLocale();
  const dict = content.locales?.[loc] || {};
  return (key) => dict[key] || key;
}

export function onContentChange(cb) {
  listeners.push(cb);
}
const listeners = [];

export function refreshI18nText() {
  const loc = getLocale();
  const dictRU = content.locales?.ru || {};
  const dictAZ = content.locales?.az || {};

  document.querySelectorAll("[data-i18n]").forEach((el) => {
    const key = el.getAttribute("data-i18n");
    const wantBoth = el.hasAttribute("data-i18n-both");

    if (wantBoth) {
      const ru = dictRU[key] || key;
      const az = dictAZ[key] || key;
      // Отрисуем оба языка аккуратно
      el.innerHTML = `
        <span>${ru}</span>
        <span style="opacity:.65; font-weight:500; margin-left:.5em;">/ ${az}</span>
      `;
    } else {
      // Старое поведение: один активный язык
      const dict = loc === "az" ? dictAZ : dictRU;
      el.textContent = dict[key] || key;
    }
  });
}
// Expose for Dashboard
export function exportJSON() {
  return JSON.stringify(content, null, 2);
}
export function importJSON(jsonStr) {
  const parsed = JSON.parse(jsonStr);
  setContent(parsed);
  refreshI18nText();
}

document.addEventListener("DOMContentLoaded", () => {
  refreshI18nText();
});
