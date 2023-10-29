import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
const Footer: React.FC = () => {
  const defaultMessage = 'Benaso\'s Project';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'CSDN',
          title: 'CSDN',
          href: 'https://blog.csdn.net/weixin_74783792?spm=1000.2115.3001.5343',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/Benaso',
          blankTarget: true,
        },
        {
          key: 'MyBlog',
          title: 'MyBlog',
          href: 'https://blog.huyoutianblog.top/',
          blankTarget: true,
        },
      ]}
    />
  );
};
export default Footer;
