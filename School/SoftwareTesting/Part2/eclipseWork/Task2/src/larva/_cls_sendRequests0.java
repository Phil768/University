package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_sendRequests0 implements _callable{

public static PrintWriter pw; 
public static _cls_sendRequests0 root;

public static LinkedHashMap<_cls_sendRequests0,_cls_sendRequests0> _cls_sendRequests0_instances = new LinkedHashMap<_cls_sendRequests0,_cls_sendRequests0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\phili\\OneDrive\\Desktop\\University\\School\\SoftwareTesting\\Part2\\eclipseWork\\Task2/src/output_sendRequests.txt");

root = new _cls_sendRequests0();
_cls_sendRequests0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_sendRequests0 parent; //to remain null - this class does not have a parent!
public static boolean blocked;
int no_automata = 1;
 public int badRequests =0 ;
public Clock blockedTime = new Clock(this,"blockedTime");

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_sendRequests0() {
}

public void initialisation() {
   blockedTime.reset();
}

public static _cls_sendRequests0 _get_cls_sendRequests0_inst() { synchronized(_cls_sendRequests0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_sendRequests0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_sendRequests0_instances){
_performLogic_sendRequestsProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_sendRequests0[] a = new _cls_sendRequests0[1];
synchronized(_cls_sendRequests0_instances){
a = _cls_sendRequests0_instances.keySet().toArray(a);}
for (_cls_sendRequests0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_sendRequests0_instances){
_cls_sendRequests0_instances.remove(this);}
synchronized(blockedTime){
blockedTime.off();
blockedTime._inst = null;
blockedTime = null;}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_sendRequestsProperty = 58;

public void _performLogic_sendRequestsProperty(String _info, int... _event) {

_cls_sendRequests0.pw.println("[sendRequestsProperty]AUTOMATON::> sendRequestsProperty("+") STATE::>"+ _string_sendRequestsProperty(_state_id_sendRequestsProperty, 0));
_cls_sendRequests0.pw.flush();

if (0==1){}
else if (_state_id_sendRequestsProperty==56){
		if (1==0){}
		else if ((_occurredEvent(_event,156/*unblockRequest*/)) && (blocked ==false &&blockedTime .compareTo (10 )>=0 )){
		badRequests =0 ;
_cls_sendRequests0.pw .println ("Back to the initial scraper state");

		_state_id_sendRequestsProperty = 58;//moving to state scraper
		_goto_sendRequestsProperty(_info);
		}
}
else if (_state_id_sendRequestsProperty==57){
		if (1==0){}
		else if ((_occurredEvent(_event,152/*goodResponse*/))){
		;
_cls_sendRequests0.pw .println ("Recieved");

		_state_id_sendRequestsProperty = 58;//moving to state scraper
		_goto_sendRequestsProperty(_info);
		}
}
else if (_state_id_sendRequestsProperty==58){
		if (1==0){}
		else if ((_occurredEvent(_event,154/*addAlert*/)) && (badRequests ==0 )){
		badRequests =0 ;
_cls_sendRequests0.pw .println ("Alerts have been added");

		_state_id_sendRequestsProperty = 58;//moving to state scraper
		_goto_sendRequestsProperty(_info);
		}
		else if ((_occurredEvent(_event,148/*badRequest*/)) && (badRequests ==0 )){
		badRequests ++;
_cls_sendRequests0.pw .println ("Bad request observed: "+badRequests );

		_state_id_sendRequestsProperty = 58;//moving to state scraper
		_goto_sendRequestsProperty(_info);
		}
		else if ((_occurredEvent(_event,148/*badRequest*/)) && (badRequests ==1 )){
		badRequests =0 ;
_cls_sendRequests0.pw .println ("Entering blocked state: "+badRequests );

		_state_id_sendRequestsProperty = 56;//moving to state blocked
		_goto_sendRequestsProperty(_info);
		}
		else if ((_occurredEvent(_event,150/*goodRequest*/)) && (badRequests ==0 )){
		;
_cls_sendRequests0.pw .println ("Good request sent");

		_state_id_sendRequestsProperty = 57;//moving to state marketAlertUM
		_goto_sendRequestsProperty(_info);
		}
}
}

public void _goto_sendRequestsProperty(String _info){
_cls_sendRequests0.pw.println("[sendRequestsProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_sendRequestsProperty(_state_id_sendRequestsProperty, 1));
_cls_sendRequests0.pw.flush();
}

public String _string_sendRequestsProperty(int _state_id, int _mode){
switch(_state_id){
case 56: if (_mode == 0) return "blocked"; else return "blocked";
case 57: if (_mode == 0) return "marketAlertUM"; else return "marketAlertUM";
case 58: if (_mode == 0) return "scraper"; else return "scraper";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}