import SwiftUI
import shared
@main
struct iOSApp: App {
    init() {
        KoinApplication.start()
    }

	var body: some Scene {
       // let _ =  KoinKt.initiOSKoin(debug: true)
      
       let __: Void = KoinKt.debugBuild()
            
		WindowGroup {
            
			ContentView()
		}
	}
}
