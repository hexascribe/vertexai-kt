//
//  Config.swift
//  ios
//
//  Created by Koji Osugi on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

enum Config {
    static var projectId: String {
        string(for: "PROJECT_ID")
    }
    
    static var accessToken: String {
        string(for: "ACCESS_TOKEN")
    }

    private static func string(for key: String) -> String {
        guard let value = ((Bundle.main.infoDictionary?[key] as? String)) else {
            fatalError("Value not found for the given key: " + key)
        }
        return value
    }
}
