"use strict";

var list = document.getElementById("nav_active");
var listItem = list.getElementsByClassName("nav_list_item");

for (var i = 0; i < listItem.length; i++) {
  listItem[i].addEventListener("click", function () {
    var current = document.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
    this.className += " active";
  });
} // -----------------active-2--------------------


var listTrend = document.getElementById("trend_active");
var listTrendItem = listTrend.getElementsByClassName("trending_list_item");

for (var _i = 0; _i < listTrendItem.length; _i++) {
  listTrendItem[_i].addEventListener("click", function () {
    var current = document.getElementsByClassName("active2");
    current[0].className = current[0].className.replace(" active2", "");
    this.className += " active2";
  });
} // -------------------Slide-Start-----------------------


$(document).ready(function () {
  $('.slideshow_banner').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    speed: 700,
    arrows: false,
    Infinity: true,
    autoplay: true,
    autoplaySpeed: 2500,
    dots: true,
    vertical: false
  });
});
$(document).ready(function () {
  $('.slideshow_product').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    speed: 600,
    Infinity: true,
    autoplay: true,
    autoplaySpeed: 3000,
    arrows: true
  });
});
$(document).ready(function () {
  $('.slideshow_product_sameKind').slick({
    slidesToShow: 4,
    slidesToScroll: 1,
    speed: 600,
    Infinity: true,
    autoplay: true,
    autoplaySpeed: 3000,
    arrows: true
  });
}); // -------------------Slide-End--------------------------
//--------------------Reponsive-Menu-------------------------------

var reponsive_menu = $('.reponsive_menu');
var btn_menu = $('.btn_menu');
var overlay_menu = $('.overlay_menu');
btn_menu.click(function (e) {
  reponsive_menu.css({
    'left': '0'
  });
  overlay_menu.css({
    'left': '0',
    'visibility': 'visible'
  }); // $('body').css({
  //   'margin-left':'300px',
  // })
});
overlay_menu.click(function () {
  reponsive_menu.css({
    'left': '-100%'
  });
  $(this).css({
    'left': '-100%',
    'visivility': 'hidden'
  }); // $('body').css({
  //   'margin-left':'0',
  // })
});