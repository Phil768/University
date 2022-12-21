package aspects;

import larva.*;
public aspect _asp_viewAlerts0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_viewAlerts0.initialize();
}
}
before () : (call(* *.viewAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_viewAlerts0.lock){

_cls_viewAlerts0 _cls_inst = _cls_viewAlerts0._get_cls_viewAlerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 174/*viewAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 174/*viewAlerts*/);
}
}
before ( boolean locked) : (call(* *.setLocked(..)) && args(locked) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_viewAlerts0.lock){

_cls_viewAlerts0 _cls_inst = _cls_viewAlerts0._get_cls_viewAlerts0_inst();
_cls_inst.locked = locked;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 176/*unlockLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 176/*unlockLogin*/);
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_viewAlerts0.lock){

_cls_viewAlerts0 _cls_inst = _cls_viewAlerts0._get_cls_viewAlerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 172/*goodLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 172/*goodLogin*/);
}
}
before () : (call(* *.loginRequest(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_viewAlerts0.lock){

_cls_viewAlerts0 _cls_inst = _cls_viewAlerts0._get_cls_viewAlerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 168/*loginRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 168/*loginRequest*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_viewAlerts0.lock){

_cls_viewAlerts0 _cls_inst = _cls_viewAlerts0._get_cls_viewAlerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 170/*badLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 170/*badLogin*/);
}
}
}