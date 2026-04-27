const tabs = document.querySelectorAll(".pickup__tab");
const contents = document.querySelectorAll(".pickup__content");

tabs.forEach(tab => {
  tab.addEventListener("click", () => {
    // タブの見た目切替
    tabs.forEach(t => t.classList.remove("pickup__tab--active"));//1回CSSの設定をリセット
    tab.classList.add("pickup__tab--active");//選択したものにだけ適用

    // コンテンツ切替
    const target = tab.dataset.target;//HTMLの属性を読み取り次に表示するコンテンツのIDを取得する
    contents.forEach(c => {
      c.classList.add("hidden");//全てのCSSのクラスにhiddenを追加して設定をリセット
      if (c.id === target) c.classList.remove("hidden");//対象のIDを持つコンテンツの設定を有効化
    });
  });
});