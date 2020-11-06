const postcssLoader = {
    loader: 'postcss-loader',
    // options: {
    //     sourceMap: options.sourceMap,
    //     plugins: [],
    // },
    plugins: {
        'autoprefixer': { browsers: 'last 5 version' }
    }
}