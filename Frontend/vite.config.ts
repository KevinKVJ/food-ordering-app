import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path from 'path'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import { NaiveUiResolver } from 'unplugin-vue-components/resolvers'
import vueJsx from '@vitejs/plugin-vue-jsx'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue({
            reactivityTransform: true
        }),
        vueJsx(),
        AutoImport({
            resolvers: [ElementPlusResolver()],
        }),
        Components({
            resolvers: [ElementPlusResolver(), NaiveUiResolver()],
            // exclude:["./src/components/**/*"]
            dirs: ['src/components','src/views'],
            dts: true,
        }),
    ],
    resolve: {
        alias: [
            { find: "@", replacement: path.resolve(__dirname, "src") }
        ]
    }
})
