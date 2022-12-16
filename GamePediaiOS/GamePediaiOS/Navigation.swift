//
//  Navigation.swift
//  GamePediaiOS
//
//  Created by Michael Ndiritu on 05/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct GamesNavigation : View{
    
    @State var navigation = NavigationPath()
    
  
    
    var body : some View{
        
        NavigationStack(path: $navigation){
    
            
            GamesScreen(nav : $navigation){ id in
                navigation.append(id)}

                
            .navigationDestination(for: Int.self){ id in
                
                Text(" The game id for this game is \(id)")
                
            }
            
        }
    }
    
}
