import * as React from "react";
import {
  View,
  Text,
  TextInput,
  SafeAreaView,
  ScrollView,
  TouchableOpacity,
  Alert,
  Button,
  FlatList,
} from "react-native";
import * as SQLite from "expo-sqlite";
import { styles } from "../../Styles";
//Returns databse object.
const db = SQLite.openDatabase("db.testDB");

export default class HomeScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: null,
    };
    db.transaction((tx) => {
      tx.executeSql(
        "CREATE TABLE IF NOT EXISTS Items (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT, count INT)"
      );
    });
    //this.fetchData();
  }
  render() {
    return (
      <View style={styles.container}>
        <Text>Add Random Name with Counts</Text>
        <TouchableOpacity onPress={this.newItem}>
          <Text>Add New Item</Text>
        </TouchableOpacity>

        <ScrollView>
          {this.state.data &&
            this.state.data.map((data) => (
              <View key={data.id}>
                <Text>
                  {data.text} - {data.count}
                </Text>
                <TouchableOpacity onPress={() => this.increment(data.id)}>
                  <Text> + </Text>
                </TouchableOpacity>
                <TouchableOpacity onPress={() => this.delete(data.id)}>
                  <Text> DEL </Text>
                </TouchableOpacity>
              </View>
            ))}
        </ScrollView>
      </View>
    );
  }

  //fetchData = () => {
  //  db.transaction((tx) => {
  //    // sending 4 arguments in executeSql
  //    tx.executeSql(
  //      "SELECT * FROM Items",
  //      null, // passing sql query and parameters:null
  //      // success callback which sends two things Transaction object and ResultSet Object
  //      (txObj, { rows: { _array } }) =>
  //        this.setState({ data: _array })(
  //          // failure callback which sends two things Transaction object and Error
  //          txObj,
  //          error
  //        ),
  //      console.log("Error ", error)
  //    ); // end executeSQL
  //  }); // end transaction
  //};
}
