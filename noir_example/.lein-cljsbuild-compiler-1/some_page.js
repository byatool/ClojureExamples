goog.provide('src_cljs.src.noir_example.some_page');
goog.require('cljs.core');
goog.require('src_cljs.src.noir_example.event');
goog.require('goog.dom');
src_cljs.src.noir_example.some_page.set_button = (function set_button(button_name){
var buttonToTest = goog.dom.getElement(button_name);
return src_cljs.src.noir_example.event.set_click.call(null,buttonToTest,(function (){
return alert("hihi");
}));
});
goog.exportSymbol('src_cljs.src.noir_example.some_page.set_button', src_cljs.src.noir_example.some_page.set_button);
