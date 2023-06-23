//
//  ContentViewModel.swift
//  ios
//
//  Created by Koji Osugi on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import VertexAI

final class ContentViewModel: ObservableObject {
    
    @Published
    var message: String = ""
    
    private var vertexAI: VertexAI {
        VertexAI.Builder()
            .setProjectId(projectId: Config.projectId)
            .setAccessToken(accessToken: Config.accessToken)
            .build()
    }
    
    private var textRequest: TextRequest {
        vertexAI.textRequest()
    }
    
    @MainActor
    func request() {
        Task.init {
            let result = try await textRequest.execute(prompt: message)
            result.onSuccess { result in
                print(result)
            }
            .onFailure { exception in
                print(exception)
            }
        }
    }
}
