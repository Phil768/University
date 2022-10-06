import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, Image } from "react-native";

export default function App() {
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <Text style={styles.test}>This is my first test</Text>
      <Text>HELLO</Text>
      <StatusBar style="auto" />
      <Image style={styles.image} source={require("./assets/favicon.png")} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  test: {
    color: "red",
    margin: 20,
  },
  image: {
    height: 100,
    width: 100,
  },
});
