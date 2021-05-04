// @ts-ignore
import { RouteRecordRaw } from "vue-router";

import Dashboard from "@/views/Dashboard.vue";
import MainLayout from "@/views/MainLayout.vue";

const routes: Array<RouteRecordRaw> = [
    {
        path: "/:any*",
        redirect: {
            name: "Dashboard"
        }
    },
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
    }
]

export default routes;