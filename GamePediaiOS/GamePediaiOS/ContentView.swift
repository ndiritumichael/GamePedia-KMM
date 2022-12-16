import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()
    let greet2 = GreetingKtor()
    @State var greeting : String = " "

	var body: some View {
     GamesNavigation()
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
