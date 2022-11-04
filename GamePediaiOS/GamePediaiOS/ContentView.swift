import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()
    let greet2 = GreetingKtor()
    @State var greeting : String = " "

	var body: some View {
        VStack{
            Text(greeting)
            Text(greet).task {
                greet2.greeting{ title, error in
                    
                    greeting = title ?? "no value found"
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
