// @ts-ignore
import { RouteRecordRaw } from "vue-router";

import Dashboard from "@/views/Dashboard.vue";
import MainLayout from "@/views/MainLayout.vue";
import Login from "@/components/auth/Login.vue";

const routes: Array<RouteRecordRaw> = [
    // {
    //     path: "/:any*",
    //     redirect: {
    //         name: "Dashboard"
    //     }
    // },
    {
        path: "/",
        redirect: { name: "Dashboard" },
        component: MainLayout,
        children: [
            {
                path: "/dashboard",
                name: "Dashboard",
                component: Dashboard
            }
        ]
    },
    {
        path: "/login",
        component: Login
    }
]

export default routes;