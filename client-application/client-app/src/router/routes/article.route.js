export default [
    {
        path: '/article',
        name: 'articleDashboard',
        component: () => import('@/views/article/dashboard'),
        children: [
            {
                name: 'listArticle',
                path: 'list',
                component: () => import('@/views/article/list'),
            },
            {
                name: 'readArticle',
                path: 'read/:id',
                component: () => import('@/views/article/read')
            },
            {
                name: 'createArticle',
                path: 'create',
                component: () => import('@/views/article/create')
            },
            {
                name: 'editArticle',
                path: 'edit/:id',
                component: () => import('@/views/article/edit')
            },
            {
                name: 'deleteArticle',
                path: 'delete/:id',
                component: () => import('@/views/article/delete')
            },
        ]
    }
]