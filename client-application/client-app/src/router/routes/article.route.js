export default [
    {
        path: '/articles',
        component: () => import('@/views/article/dashboard'),
        children: [
            {
                name: 'listArticle',
                path: '',
                component: () => import('@/views/article/list'),
            },
            {
                name: 'readArticle',
                path: ':id',
                component: () => import('@/views/article/read')
            },
        ]
    }
]