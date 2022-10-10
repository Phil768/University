import { StyleSheet, Text, View, Image, Button } from "react-native";

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  header: {
    backgroundColor: "#f4511e",
    textAlign: "center",
  },
  navigationBar: {
    backgroundColor: "red",
    padding: 20,
  },
  buttons: {
    margin: 20,
  },
  input: {
    borderWidth: "1px",
    borderColor: "black",
    padding: 20,
    margin: 20,
    maxWidth: "100%",
    alignItems: "left",
  },
});
