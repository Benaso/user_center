import { Settings as LayoutSettings } from '@ant-design/pro-components';

const Settings: LayoutSettings & {
  pwa?: boolean;
  logo?: string;
} = {
  navTheme: 'light',
  // 拂晓蓝
  primaryColor: '#1890ff',
  layout: 'mix',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: true,
  colorWeak: false,
  title: '用户管理中心',
  pwa: false,
  logo: 'https://picx.zhimg.com/80/v2-97037e391753aa217d383cf3e45876ad_720w.webp?source=1940ef5c',
  iconfontUrl: '',
};

export default Settings;
