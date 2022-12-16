//
//  GamesViewModel.swift
//  GamePediaiOS
//
//  Created by Michael Ndiritu on 24/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

@MainActor
class GamesViewModel : ObservableObject {
    
    @LazyKoin var presenter : GamesPresenters
    
    
    @Published var games = [Game]()
    var hasNextPage : Bool = false
    
    
    @MainActor
    func loadGames(){
        
     Task {
         self.presenter.iosGamesData.watch{ controller in
             
             
             controller?.pagingDataIos.watch{nullablePagingData in
                 
                 
                 guard let games = nullablePagingData?.compactMap({$0 as? Game}) else{
                     return
                 }
                 
                 
                 DispatchQueue.main.async{
                     self.games = games
                     self.hasNextPage = self.presenter.gamesDataValue.pager.hasNextPage
                 }
                
                
                 
                 
             }
         }
        }
    }
        
        func fetchNextData() {
            
        
            self.presenter.gamesDataValue.pager.loadNext()
           }


           public var shouldDisplayNextPage: Bool {
               return hasNextPage
           }
    
    
    
    func searchGames(text: String){
        withAnimation{
            games = []
        }
        hasNextPage = false
        presenter.searchGames(text: text)    }
    
    
    
    
    
}

