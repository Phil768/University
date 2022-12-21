package aspects;

import larva.*;
public aspect _asp_SystemMonitor(send)0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_SystemMonitor(send)0.initialize();
}
}
before () : (call(* *.goodResponse(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor(send)0.lock){

_cls_SystemMonitor(send)0 _cls_inst = _cls_SystemMonitor(send)0._get_cls_SystemMonitor(send)0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 144/*goodResponse*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 144/*goodResponse*/);
}
}
before () : (call(* *.goodRequest(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor(send)0.lock){

_cls_SystemMonitor(send)0 _cls_inst = _cls_SystemMonitor(send)0._get_cls_SystemMonitor(send)0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 142/*goodRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 142/*goodRequest*/);
}
}
before ( boolean blocked) : (call(* *.setBlocked(..)) && args(blocked) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor(send)0.lock){

_cls_SystemMonitor(send)0 _cls_inst = _cls_SystemMonitor(send)0._get_cls_SystemMonitor(send)0_inst();
_cls_inst.blocked = blocked;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 148/*unblockRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 148/*unblockRequest*/);
}
}
before () : (call(* *.badRequest(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor(send)0.lock){

_cls_SystemMonitor(send)0 _cls_inst = _cls_SystemMonitor(send)0._get_cls_SystemMonitor(send)0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 140/*badRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 140/*badRequest*/);
}
}
before () : (call(* *.addAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor(send)0.lock){

_cls_SystemMonitor(send)0 _cls_inst = _cls_SystemMonitor(send)0._get_cls_SystemMonitor(send)0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 146/*addAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 146/*addAlert*/);
}
}
}