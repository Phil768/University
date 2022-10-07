import { StatusBar } from "expo-status-bar";
import { StyleSheet, Text, View, Image, Button } from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
//classes
import Details from "./Details";
import { styles } from "./Styles";
import NavigationBar from "./navigationBar";

function App({ navigation }) {
  return (
    <View style={styles.container}>
      <Text>Home</Text>
      <NavigationBar />
    </View>
  );
}

function Nav() {
  const Stack = createNativeStackNavigator();
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen
          name="Home"
          component={App}
          options={{
            title: "Home Screen",
            headerStyle: styles.header,
            headerTitleStyle: { fontWeight: "bold" },
          }}
        />
        <Stack.Screen name="Details" component={Details} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default Nav;
