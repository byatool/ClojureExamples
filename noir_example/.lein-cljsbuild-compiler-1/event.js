goog.provide('src_cljs.src.noir_example.event');
goog.require('cljs.core');
goog.require('goog.events');
goog.require('goog.dom');
src_cljs.src.noir_example.event.set_click = (function set_click(element,method){
return goog.events.listen(element,goog.events.EventType.CLICK,method);
});
goog.exportSymbol('src_cljs.src.noir_example.event.set_click', src_cljs.src.noir_example.event.set_click);
