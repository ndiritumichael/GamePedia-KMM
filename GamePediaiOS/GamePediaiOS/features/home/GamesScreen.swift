//
//  GamesScreen.swift
//  GamePediaiOS
//
//  Created by Michael Ndiritu on 24/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import CachedAsyncImage

struct GamesScreen: View {
    
    
    @StateObject var viewModel : GamesViewModel = GamesViewModel()
    @Binding var nav : NavigationPath
    let onClick : (Int) -> Void
    
    @State var searchText = ""
    
  //  @LazyKoin var presenter : GamesPresenters
    var body: some View {
        
        ScrollView {
            LazyVStack {
                
                
                ForEach(viewModel.games,id: \.id){ game in
                    GameScreen(game: game){id in
                        
                        nav.append(id)
                        
                    }
                }
                
                if viewModel.shouldDisplayNextPage {
                               nextPageView
                           }
            }.onAppear{
                viewModel.loadGames()
        }
        }.navigationBarTitle(Text(searchText.isEmpty ? "Games" : searchText ))
            .searchable(text: $searchText, prompt: "Search for video games")
            .onChange(of: searchText){text in
                
                viewModel.searchGames(text: text)
            }
    }
    
    
    
     private var nextPageView: some View {
         HStack {
             Spacer()
             VStack {
                 ProgressView()
                 Text("Loading next page...")
             }
             Spacer()
         }
         .onAppear(perform: {
             viewModel.fetchNextData()
         })
     }
}

struct GamesScreen_Previews: PreviewProvider {
    
    @State var nav  = NavigationPath()
    static var previews: some View {
        Text(" hi")
       
      //  GamesScreen(navigation: $nav)
    }
}


struct GameScreen : View {
    let game: Game
    let onClick : (Int) -> Void
    @State var showPlaceHolder: Bool = true
    
    var body: some View {
        
        ZStack(alignment : .leading){
            CachedAsyncImage(url: URL(string: game.backgroundImage)){ image  in
                image.resizable().scaledToFill().frame( width : UIScreen.main.bounds.width - 20, height: 250, alignment: .center)
                
                    .clipped()
                    .cornerRadius(15)
                    .onAppear{
                        withAnimation{
                            showPlaceHolder.toggle()
                        }
                    }
                
                
            } placeholder : {
                GamePlaceHolder().frame( width : UIScreen.main.bounds.width - 20, height: 250, alignment: .center).shimmer()
            }
            .overlay(LinearGradient(gradient: Gradient(colors: [.clear, .black.opacity(0.5),.black]), startPoint: .center, endPoint: .bottom)).cornerRadius(15)
                // .clipped()
                
                VStack(alignment : .leading,spacing: 2){
                    Spacer()
                  
                        
                    HStack{
                        ForEach(game.platforms,id: \.self){ platform in
                            Image(getPlatformLogo(name: platform)).resizable().renderingMode(.template).frame(width : 20,height: 20).foregroundColor(.white)
                          
                            
                            
                        }
                        
                    }
                    
                    Text(game.name).fontWeight(.bold)
                        .foregroundColor(.white)
                       
             
                        FiveStarView(rating: Decimal(Double(truncating: game.rating as NSNumber)),color: .yellow)
                            .frame(width: 70, height: 15).padding(.leading, 20)
                    
                    
                    
                }.padding(.horizontal)
                    .padding(.bottom,10)
            if let score = game.scoreRating?.metaCriticScore{
                MetacriticScore(score: Int(score) ).frame(maxWidth : .infinity,maxHeight: .infinity,alignment: .bottomTrailing).padding([.bottom,.trailing],10)
            }
                
                
                
        } .frame(width :  UIScreen.main.bounds.width-20 ,height: 250,alignment: .center)
            
                .shadow(radius: 10)
                .onTapGesture {
                    onClick(Int(game.id))
                }
                //.redacted(reason: showPlaceHolder ? .placeholder : [])
            
            
            
            
            
            
        
    }
    
}


struct MetacriticScore : View{
    let score : Int
    
    var body : some View{
        
        ZStack{
   
            Text("\(score)").foregroundColor(score.getScoreColor())
                .padding(5)
                
             
                .overlay(
                    Capsule()
                        .stroke(score.getScoreColor(), lineWidth: 1)
                        .frame(width : 40)
                       
                ).padding(5)
            
        }
        
           
        
    }
}


struct GamePlaceHolder :View{
    var body: some View{
        Rectangle().frame(width: UIScreen.main.bounds.width - 50,height: 200,alignment: .center).padding(50).foregroundColor(Color.gray.opacity(0.25))
    }
}
