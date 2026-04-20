// スライドに使う画像一覧
const heroImages = [
  "/img/banner1.jpg",
  "/img/banner2.jpg",
  "/img/banner3.jpg",
];

// ランダム開始
let currentIndex = Math.floor(Math.random() * heroImages.length);

const heroImage = document.getElementById("heroImage");
const prevBtn = document.getElementById("prevBtn");
const nextBtn = document.getElementById("nextBtn");

// 画像更新
function updateHeroImage() {
  heroImage.src = heroImages[currentIndex];
}

// 次へ
nextBtn.addEventListener("click", () => {
  currentIndex = (currentIndex + 1) % heroImages.length;
  updateHeroImage();
});

// 前へ
prevBtn.addEventListener("click", () => {
  currentIndex = (currentIndex - 1 + heroImages.length) % heroImages.length;
  updateHeroImage();
});

// 自動スライド（3秒ごと）
setInterval(() => {
  currentIndex = (currentIndex + 1) % heroImages.length;
  updateHeroImage();
}, 3000);

// 初期表示
updateHeroImage();