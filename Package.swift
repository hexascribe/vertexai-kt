// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "VertexAI",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "VertexAI",
            targets: ["VertexAI"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "VertexAI",
            path: "./VertexAI.xcframework"
        ),
    ]
)
