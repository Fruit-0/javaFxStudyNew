module.exports = {
  base: "/JavaFX-For-Dummies-zh/",
  head: [
    ['link', { rel: 'icon', href: '/favicon.png' }]
  ],
  title: 'JavaFX For Dummies',
  description: '《JavaFX For Dummies》中文版',
  // 多语言设置
  locales: {
    '/': {
      lang: 'zh-CN'
    },
    '/en/': {
      lang: 'en-US'
    }
  },
  // 主题配置
  themeConfig: {
    logo: '/favicon.png',
    // 仓库相关
    repo: 'Angus-Liu/JavaFX-For-Dummies-zh',
    docsBranch: 'main',
    docsDir: 'docs',
    editLinks: true,
    locales: {
      '/': {
        selectText: '选择语言',
        label: '简体中文',
        nav: [
          { text: '首页', link: '/' },
          { text: '正文', link: '/Introduction' },
          { text: '购买本书', link: 'https://www.amazon.com/JavaFX-Dummies-Doug-Lowe/dp/1118385349' },
        ],
        editLinkText: '翻译有错？帮助改进:)',
        // 侧边栏配置
        sidebar: {
          '/': [
            'Introduction',
            'Part-1',
            'Chapter-1',
            'Chapter-2',
            'Chapter-3',
            'Chapter-4',
            'Chapter-5',
            'Chapter-6',
            'Part-2',
            'Chapter-7',
            'Chapter-8',
            'Chapter-9',
            'Chapter-10',
            'Part-3',
            'Chapter-11',
            'Chapter-12',
            'Chapter-13',
            'Chapter-14',
            'Part-4',
            'Chapter-15',
            'Chapter-16',
            'Chapter-17',
            'Chapter-18',
            'Part-5',
            'Chapter-19',
            'Chapter-20',
            'About-the-Author',
          ]
        }
      },
      '/en/': {
        selectText: 'Languages',
        label: 'English',
        ariaLabel: 'Languages',
        nav: [
          { text: 'Home', link: '/en/' },
          { text: 'Introduction', link: '/en/Introduction' },
          { text: 'Buy', link: 'https://www.amazon.com/JavaFX-Dummies-Doug-Lowe/dp/1118385349' },
        ],
        editLinkText: 'Edit this page on GitHub :)',
        sidebar: {
          '/en/': [
            'Introduction',
            'Part-1',
            'Chapter-1',
            'Chapter-2',
            'Chapter-3',
            'Chapter-4',
            'Chapter-5',
            'Chapter-6',
            'Part-2',
            'Chapter-7',
            'Chapter-8',
            'Chapter-9',
            'Chapter-10',
            'Part-3',
            'Chapter-11',
            'Chapter-12',
            'Chapter-13',
            'Chapter-14',
            'Part-4',
            'Chapter-15',
            'Chapter-16',
            'Chapter-17',
            'Chapter-18',
            'Part-5',
            'Chapter-19',
            'Chapter-20',
            'About-the-Author',
          ]
        }
      }
    },
  },
  // markdown 配置
  markdown: {
    // lineNumbers: true
  },
  // 插件配置
  plugins: {
  },
}
