package aspects;

import main.Bank;
import main.User;
import main.Account;

import larva.*;
public aspect _asp_StudentBank2 {

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_StudentBank2.initialize();
}
}
before ( Account a1) : (call(* Account.deleteTransaction(..)) && target(a1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_StudentBank0.lock){
Account a;
User u;
a =a1 ;
u =a .owner ;

_cls_StudentBank2 _cls_inst = _cls_StudentBank2._get_cls_StudentBank2_inst( a,u);
_cls_inst.a1 = a1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 36/*deleteTransaction*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 36/*deleteTransaction*/);
}
}
before ( Account a1) : (call(* Account.addTransaction(..)) && target(a1) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_StudentBank0.lock){
Account a;
User u;
a =a1 ;
u =a .owner ;

_cls_StudentBank2 _cls_inst = _cls_StudentBank2._get_cls_StudentBank2_inst( a,u);
_cls_inst.a1 = a1;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 34/*addTransaction*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 34/*addTransaction*/);
}
}
}