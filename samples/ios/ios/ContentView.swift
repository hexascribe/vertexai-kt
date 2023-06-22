import SwiftUI
import VertexAI

struct ContentView: View {
    
    @ObservedObject
    private var viewModel: ContentViewModel
    
    init(viewModel: ContentViewModel = .init()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack(alignment: .center) {
            Text("VertexAI Sample")
                .padding(.bottom, 24)
            TextField(text: $viewModel.message) {
                Text("Your prompt")
            }.padding(.bottom, 16)
            .textFieldStyle(DefaultTextFieldStyle())
            Button("Tap to request") {
                viewModel.request()
            }
            Spacer()
        }
        .padding(24)
    }
}

struct DefaultTextFieldStyle: TextFieldStyle {
    func _body(configuration: TextField<Self._Label>) -> some View {
        configuration
            .padding(.vertical)
            .padding(.horizontal, 24)
            .background(Color.gray.opacity(0.2))
            .cornerRadius(8)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
