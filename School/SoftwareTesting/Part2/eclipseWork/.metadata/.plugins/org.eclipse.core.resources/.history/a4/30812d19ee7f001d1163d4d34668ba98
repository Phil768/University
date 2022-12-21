package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_SystemMonitor(send)0 implements _callable{

public static PrintWriter pw; 
public static _cls_SystemMonitor(send)0 root;

public static LinkedHashMap<_cls_SystemMonitor(send)0,_cls_SystemMonitor(send)0> _cls_SystemMonitor(send)0_instances = new LinkedHashMap<_cls_SystemMonitor(send)0,_cls_SystemMonitor(send)0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\phili\\OneDrive\\Desktop\\University\\School\\SoftwareTesting\\Part2\\eclipseWork\\Task2/src/output_SystemMonitor(send).txt");

root = new _cls_SystemMonitor(send)0();
_cls_SystemMonitor(send)0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_SystemMonitor(send)0 parent; //to remain null - this class does not have a parent!
public static boolean blocked;
int no_automata = 1;
 public int badRequests =0 ;
public Clock blockedTime = new Clock(this,"blockedTime");

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_SystemMonitor(send)0() {
}

public void initialisation() {
   blockedTime.reset();
}

public static _cls_SystemMonitor(send)0 _get_cls_SystemMonitor(send)0_inst() { synchronized(_cls_SystemMonitor(send)0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_SystemMonitor(send)0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_SystemMonitor(send)0_instances){
_performLogic_sendAlertsProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_SystemMonitor(send)0[] a = new _cls_SystemMonitor(send)0[1];
synchronized(_cls_SystemMonitor(send)0_instances){
a = _cls_SystemMonitor(send)0_instances.keySet().toArray(a);}
for (_cls_SystemMonitor(send)0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_SystemMonitor(send)0_instances){
_cls_SystemMonitor(send)0_instances.remove(this);}
synchronized(blockedTime){
blockedTime.off();
blockedTime._inst = null;
blockedTime = null;}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_sendAlertsProperty = 68;

public void _performLogic_sendAlertsProperty(String _info, int... _event) {

_cls_SystemMonitor(send)0.pw.println("[sendAlertsProperty]AUTOMATON::> sendAlertsProperty("+") STATE::>"+ _string_sendAlertsProperty(_state_id_sendAlertsProperty, 0));
_cls_SystemMonitor(send)0.pw.flush();

if (0==1){}
else if (_state_id_sendAlertsProperty==66){
		if (1==0){}
		else if ((_occurredEvent(_event,148/*unblockRequest*/)) && (blocked ==false &&blockedTime .compareTo (10 )<0 )){
		badRequests =0 ;
_cls_SystemMonitor(send)0.pw .println ("Back to the initial scraper state");

		_state_id_sendAlertsProperty = 68;//moving to state scraper
		_goto_sendAlertsProperty(_info);
		}
}
else if (_state_id_sendAlertsProperty==67){
		if (1==0){}
		else if ((_occurredEvent(_event,144/*goodResponse*/))){
		;
_cls_SystemMonitor(send)0.pw .println ("Recieved");

		_state_id_sendAlertsProperty = 68;//moving to state scraper
		_goto_sendAlertsProperty(_info);
		}
}
else if (_state_id_sendAlertsProperty==68){
		if (1==0){}
		else if ((_occurredEvent(_event,146/*addAlert*/))){
		badRequests =0 ;
_cls_SystemMonitor(send)0.pw .println ("Alerts have been added");

		_state_id_sendAlertsProperty = 68;//moving to state scraper
		_goto_sendAlertsProperty(_info);
		}
		else if ((_occurredEvent(_event,140/*badRequest*/)) && (badRequests <1 )){
		badRequests ++;
_cls_SystemMonitor(send)0.pw .println ("Bad request observed");

		_state_id_sendAlertsProperty = 68;//moving to state scraper
		_goto_sendAlertsProperty(_info);
		}
		else if ((_occurredEvent(_event,148/*unblockRequest*/)) && (badRequests ==1 &&blocked ==false &&blockedTime .compareTo (10 )<0 )){
		badRequests =0 ;
_cls_SystemMonitor(send)0.pw .println ("Entering blocked state");

		_state_id_sendAlertsProperty = 66;//moving to state blocked
		_goto_sendAlertsProperty(_info);
		}
		else if ((_occurredEvent(_event,142/*goodRequest*/))){
		;
_cls_SystemMonitor(send)0.pw .println ("Good request sent");

		_state_id_sendAlertsProperty = 67;//moving to state marketAlertUM
		_goto_sendAlertsProperty(_info);
		}
}
}

public void _goto_sendAlertsProperty(String _info){
_cls_SystemMonitor(send)0.pw.println("[sendAlertsProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_sendAlertsProperty(_state_id_sendAlertsProperty, 1));
_cls_SystemMonitor(send)0.pw.flush();
}

public String _string_sendAlertsProperty(int _state_id, int _mode){
switch(_state_id){
case 66: if (_mode == 0) return "blocked"; else return "blocked";
case 67: if (_mode == 0) return "marketAlertUM"; else return "marketAlertUM";
case 68: if (_mode == 0) return "scraper"; else return "scraper";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}