import SwiftUI
import shared
@main
struct iOSApp: App {


	var body: some Scene {
       let _ =  KoinKt.doInitKoin()
        let __: Void = KoinKt.debugBuild()
            
		WindowGroup {
            
			ContentView()
		}
	}
}
