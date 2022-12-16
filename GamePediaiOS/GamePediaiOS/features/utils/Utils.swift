//
//  Utils.swift
//  GamePediaiOS
//
//  Created by Michael Ndiritu on 06/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation



func getPlatformLogo(name: String)-> String{
    
    switch name{
    case "pc":
        return "windows-10"
    case "playstation":
        return "pslogo"
    case "xbox":
        return "xboxlogo"
    case "nintendo":
        return "nintendologo"
    case "android":
        return "androidlogo"
    case "mac":
        return "applelogo"
    case "linux":
        return "linuxlogo"
    default :
        return "browser"
    }
    
}
