import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, Image, Button } from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
//classes
import { styles } from "./Styles";
import Nav from "./App";
import Details from "./Details";

export default function NavigationBar({ navigation }) {
  return (
    <View style={styles.navigationBar}>
      <Button
        style={styles.buttons}
        title="Details"
        onPress={() => {
          navigation.navigate("Details");
        }}
      />
      <Button
        style={styles.buttons}
        title="Details"
        onPress={() => {
          navigation.navigate("Details");
        }}
      />
    </View>
  );
}

<Nav />;
