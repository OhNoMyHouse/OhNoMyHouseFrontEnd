import type { NuxtConfig } from "@nuxt/types";
import type { ModuleOptions as ImageOptions } from "@nuxt/image";

const ImageConfig: Partial<ImageOptions> = { domains: ["i.ytimg.com"] };

const Config: NuxtConfig = {
  target: "static",
  server: { port: 8080 },
  dir: { static: "public" },
  loading: { color: "#d93025" },

  head: {
    title: "YouTube Concept",
    meta: [
      { charset: "utf-8" },
      { name: "viewport", content: "width=device-width, initial-scale=1" },
      {
        hid: "description",
        name: "description",
        content: "A modern YouTube concept",
      },
    ],

    link: [{ rel: "icon", type: "image/x-icon", href: "/favicon.png" }],
    script: [
      {
        src: "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=13195c6e1a069f22f4340a26aec44a00&libraries=services",
      },
    ],
  },

  components: true,
  css: ["@/assets/css/main"],
  buildModules: ["@nuxt/typescript-build", ["@nuxt/image", ImageConfig]],

  modules: ["bootstrap-vue/nuxt"],
};

export default Config;
