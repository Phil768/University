package aspects;

import larva.*;
public aspect _asp_sendRequests0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_sendRequests0.initialize();
}
}
before () : (call(* *.goodResponse(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_sendRequests0.lock){

_cls_sendRequests0 _cls_inst = _cls_sendRequests0._get_cls_sendRequests0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 152/*goodResponse*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 152/*goodResponse*/);
}
}
before () : (call(* *.goodRequest(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_sendRequests0.lock){

_cls_sendRequests0 _cls_inst = _cls_sendRequests0._get_cls_sendRequests0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 150/*goodRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 150/*goodRequest*/);
}
}
before ( boolean blocked) : (call(* *.setBlocked(..)) && args(blocked) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_sendRequests0.lock){

_cls_sendRequests0 _cls_inst = _cls_sendRequests0._get_cls_sendRequests0_inst();
_cls_inst.blocked = blocked;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 156/*unblockRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 156/*unblockRequest*/);
}
}
before () : (call(* *.badRequest(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_sendRequests0.lock){

_cls_sendRequests0 _cls_inst = _cls_sendRequests0._get_cls_sendRequests0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 148/*badRequest*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 148/*badRequest*/);
}
}
before () : (call(* *.addAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_sendRequests0.lock){

_cls_sendRequests0 _cls_inst = _cls_sendRequests0._get_cls_sendRequests0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 154/*addAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 154/*addAlert*/);
}
}
}