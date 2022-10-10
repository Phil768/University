import * as React from "react";
import {
  View,
  Text,
  TextInput,
  SafeAreaView,
  Alert,
  Button,
} from "react-native";
import * as firebase from "firebase/app";

import { styles } from "../../Styles";

const firebaseConfig = {
  apiKey: "AIzaSyDlOGl8H_Nx7eoZNTNlx9UEhsUTcOifjhk",
  authDomain: "reactapp-820ce.firebaseapp.com",
  projectId: "reactapp-820ce",
  storageBucket: "reactapp-820ce.appspot.com",
  messagingSenderId: "712993072723",
  appId: "1:712993072723:web:7965f28a2f723fb515c8a6",
  measurementId: "G-TW4VX3ZHC4",
};

firebase.initializeApp(firebaseConfig);

//database
//import SQLite from "expo-sqlite";
//
//const database = SQLite.openDatabase(
//  {
//    name: "MainDB",
//    version: 1,
//  },
//  () => {},
//  (error) => console.log(error)
//);
//
////States create opbjects which can be edited on screen.
//export default function HomeScreen({ navigation }) {
//  const [name, setName] = useState("");
//  const [age, setage] = useState("");
//
//  React.useEffect(() => {
//    createTable();
//  }, []);
//
//  const createTable = () => {
//    database.transaction((tx) => {
//      tx.executeSql(
//        "CREATE TABLE IF NOT EXISTS " +
//          "Users " +
//          "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Age INTEGER);"
//      );
//    });
//  };
//
//  const setData = async () => {
//    if (name.length == 0 || age.length == 0) {
//      Alert.alert("Warning!", "Please insert some data.");
//    } else {
//      try {
//        await database.transaction(async (tx) => {
//          await tx.executeSql("INSERT INTO Users (Name, Age) VALUES (?,?)", [
//            name,
//            age,
//          ]);
//        });
//      } catch (error) {
//        console.log(error);
//      }
//    }
//  };
//
//  const getData = async () => {
//    try {
//      await database.transaction(async (tx) => {
//        await tx.executeSql("SELECT * FROM Users"),
//          [],
//          (tx, results) => {
//            var len = results.rows.length;
//            if (len > 0) {
//              var userName = results.rows.item(0).Name;
//              var userAge = results.rows.item(0).Age;
//              setName(userName);
//              setage(userAge);
//            }
//          };
//      });
//    } catch (err) {
//      console.log(err);
//    }
//  };
export default function HomeScreen({ navigation }) {
  return (
    <SafeAreaView style={styles.container}>
      <TextInput placeholder="Email" style={styles.input} />
      <TextInput placeholder="Password" style={styles.input} />
      <Button title="Login" />
      <Button title="Sign up" />
    </SafeAreaView>
  );
}
