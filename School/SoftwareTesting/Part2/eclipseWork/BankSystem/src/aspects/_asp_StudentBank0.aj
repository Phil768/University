package aspects;

import main.Bank;
import main.User;
import main.Account;

import larva.*;
public aspect _asp_StudentBank0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_StudentBank0.initialize();
}
}
before () : (call(* *.deleteUser(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_StudentBank0.lock){

_cls_StudentBank0 _cls_inst = _cls_StudentBank0._get_cls_StudentBank0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 26/*deleteUser*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 26/*deleteUser*/);
}
}
before () : (call(* *.addUser(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_StudentBank0.lock){

_cls_StudentBank0 _cls_inst = _cls_StudentBank0._get_cls_StudentBank0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 24/*addUser*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 24/*addUser*/);
}
}
}