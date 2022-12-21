package aspects;

import larva.*;
public aspect _asp_SystemMonitor0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_SystemMonitor0.initialize();
}
}
before () : (call(* *.goodResponse(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor0.lock){

_cls_SystemMonitor0 _cls_inst = _cls_SystemMonitor0._get_cls_SystemMonitor0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 164/*goodResponse*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 164/*goodResponse*/);
}
}
before () : (call(* *.goodRequest(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor0.lock){

_cls_SystemMonitor0 _cls_inst = _cls_SystemMonitor0._get_cls_SystemMonitor0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 162/*goodRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 162/*goodRequest*/);
}
}
before ( boolean blocked) : (call(* *.setBlocked(..)) && args(blocked) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor0.lock){

_cls_SystemMonitor0 _cls_inst = _cls_SystemMonitor0._get_cls_SystemMonitor0_inst();
_cls_inst.blocked = blocked;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 168/*unblockRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 168/*unblockRequest*/);
}
}
before () : (call(* *.badRequest(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor0.lock){

_cls_SystemMonitor0 _cls_inst = _cls_SystemMonitor0._get_cls_SystemMonitor0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 160/*badRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 160/*badRequest*/);
}
}
before () : (call(* *.addAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_SystemMonitor0.lock){

_cls_SystemMonitor0 _cls_inst = _cls_SystemMonitor0._get_cls_SystemMonitor0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 166/*addAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 166/*addAlert*/);
}
}
}