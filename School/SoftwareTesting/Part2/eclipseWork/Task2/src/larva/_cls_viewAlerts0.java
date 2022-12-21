package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_viewAlerts0 implements _callable{

public static PrintWriter pw; 
public static _cls_viewAlerts0 root;

public static LinkedHashMap<_cls_viewAlerts0,_cls_viewAlerts0> _cls_viewAlerts0_instances = new LinkedHashMap<_cls_viewAlerts0,_cls_viewAlerts0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\phili\\OneDrive\\Desktop\\University\\School\\SoftwareTesting\\Part2\\eclipseWork\\Task2/src/output_viewAlerts.txt");

root = new _cls_viewAlerts0();
_cls_viewAlerts0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_viewAlerts0 parent; //to remain null - this class does not have a parent!
public static boolean locked;
int no_automata = 1;
 public int badLogins =0 ;
 public int goodLogins =0 ;
public Clock lockedTime = new Clock(this,"lockedTime");

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_viewAlerts0() {
}

public void initialisation() {
   lockedTime.reset();
}

public static _cls_viewAlerts0 _get_cls_viewAlerts0_inst() { synchronized(_cls_viewAlerts0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_viewAlerts0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_viewAlerts0_instances){
_performLogic_viewAlertsProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_viewAlerts0[] a = new _cls_viewAlerts0[1];
synchronized(_cls_viewAlerts0_instances){
a = _cls_viewAlerts0_instances.keySet().toArray(a);}
for (_cls_viewAlerts0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_viewAlerts0_instances){
_cls_viewAlerts0_instances.remove(this);}
synchronized(lockedTime){
lockedTime.off();
lockedTime._inst = null;
lockedTime = null;}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_viewAlertsProperty = 66;

public void _performLogic_viewAlertsProperty(String _info, int... _event) {

_cls_viewAlerts0.pw.println("[viewAlertsProperty]AUTOMATON::> viewAlertsProperty("+") STATE::>"+ _string_viewAlertsProperty(_state_id_viewAlertsProperty, 0));
_cls_viewAlerts0.pw.flush();

if (0==1){}
else if (_state_id_viewAlertsProperty==64){
		if (1==0){}
		else if ((_occurredEvent(_event,176/*unlockLogin*/)) && (locked ==false &&lockedTime .compareTo (10 )>=0 )){
		badLogins =0 ;
_cls_viewAlerts0.pw .println ("Back to the login state");

		_state_id_viewAlertsProperty = 63;//moving to state login
		_goto_viewAlertsProperty(_info);
		}
}
else if (_state_id_viewAlertsProperty==66){
		if (1==0){}
		else if ((_occurredEvent(_event,168/*loginRequest*/))){
		badLogins =0 ;
_cls_viewAlerts0.pw .println ("Login reuqest has been recorded");

		_state_id_viewAlertsProperty = 63;//moving to state login
		_goto_viewAlertsProperty(_info);
		}
}
else if (_state_id_viewAlertsProperty==63){
		if (1==0){}
		else if ((_occurredEvent(_event,170/*badLogin*/)) && (badLogins ==0 )){
		badLogins ++;
_cls_viewAlerts0.pw .println ("Bad login observed");

		_state_id_viewAlertsProperty = 63;//moving to state login
		_goto_viewAlertsProperty(_info);
		}
		else if ((_occurredEvent(_event,170/*badLogin*/)) && (badLogins ==1 )){
		badLogins =0 ;
_cls_viewAlerts0.pw .println ("Entering blocked state");

		_state_id_viewAlertsProperty = 64;//moving to state blocked
		_goto_viewAlertsProperty(_info);
		}
		else if ((_occurredEvent(_event,172/*goodLogin*/))){
		goodLogins ++;
_cls_viewAlerts0.pw .println ("Good login recorded");

		_state_id_viewAlertsProperty = 63;//moving to state login
		_goto_viewAlertsProperty(_info);
		}
		else if ((_occurredEvent(_event,174/*viewAlerts*/)) && (goodLogins ==1 )){
		;
_cls_viewAlerts0.pw .println ("Viewing alerts!");

		_state_id_viewAlertsProperty = 65;//moving to state alerts
		_goto_viewAlertsProperty(_info);
		}
}
}

public void _goto_viewAlertsProperty(String _info){
_cls_viewAlerts0.pw.println("[viewAlertsProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_viewAlertsProperty(_state_id_viewAlertsProperty, 1));
_cls_viewAlerts0.pw.flush();
}

public String _string_viewAlertsProperty(int _state_id, int _mode){
switch(_state_id){
case 65: if (_mode == 0) return "alerts"; else return "alerts";
case 64: if (_mode == 0) return "blocked"; else return "blocked";
case 66: if (_mode == 0) return "marketAlertUM"; else return "marketAlertUM";
case 63: if (_mode == 0) return "login"; else return "login";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}