export default [
  {
    path: '/user',
    layout: false,
    routes: [
      { name: "登录", path: '/user/login', component: './user/Login' },
      { name: "注册", path: '/user/register', component: './user/Register' },
      { component: './404' },
    ],
  },
  {
    path: '/welcome',
    icon: 'smile',
    component: './Welcome',
    menu: {
      name: '欢迎使用', // 你希望显示的菜单项文本
    },
  },
  {
    path: '/admin',
    name:'管理页面',
    icon: 'crown',
    access: 'canAdmin',
    component: './Admin',
    routes: [
      { path: '/admin/user-manage', name:'用户管理',icon: 'smile', component: './Admin/UserManage' },
        { component: './404' },
    ],
  },
  { path: '/', redirect: '/welcome' },
  { component: './404' },
];
