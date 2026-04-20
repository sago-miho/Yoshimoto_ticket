const tabs = document.querySelectorAll(".pickup__tab");
const contents = document.querySelectorAll(".pickup__content");

tabs.forEach(tab => {
  tab.addEventListener("click", () => {
    // タブの見た目切替
    tabs.forEach(t => t.classList.remove("pickup__tab--active"));
    tab.classList.add("pickup__tab--active");

    // コンテンツ切替
    const target = tab.dataset.target;
    contents.forEach(c => {
      c.classList.add("hidden");
      if (c.id === target) c.classList.remove("hidden");
    });
  });
});