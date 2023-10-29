import type { ActionType, ProColumns } from '@ant-design/pro-components';
import { ProTable } from '@ant-design/pro-components';
import {Image} from 'antd';
import { useRef } from 'react';
import {removeUser, searchUsers} from "@/services/ant-design-pro/api";


export const waitTimePromise = async (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

export const waitTime = async (time: number = 100) => {
  await waitTimePromise(time);
};

const columns: ProColumns<API.CurrentUser>[] = [
  {
    dataIndex: 'id',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '用户名',
    dataIndex: 'username',
    copyable: true,//是否允许复制
    ellipsis: true,
    tip: '标题过长会自动收缩',
  },
  {
    title: '用户账户',
    dataIndex: 'userAccount',
    copyable: true,//是否允许复制
    ellipsis: true,
    tip: '标题过长会自动收缩',
  },
  {
    title: '头像',
    dataIndex: 'avatarUrl',
    copyable: true,//是否允许复制
    render:(_, record) =>(
      <div>
        <Image src={record.avatarUrl} width={80} height={80}/>
      </div>
    ),
  },
  {
    title: '性别',
    dataIndex: 'gender',
    copyable: true,//是否允许复制
    valueType: 'select',
    valueEnum: {
      0: { text: '女'},
      1: { text: '男',},
    },
  },
  {
    title: '电话',
    dataIndex: 'phone',
    copyable: true,//是否允许复制
    ellipsis: true,
    tip: '标题过长会自动收缩',
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    copyable: true,//是否允许复制
    ellipsis: true,
    tip: '标题过长会自动收缩',
  },
  {
    title: '状态',
    dataIndex: 'userStatus',
  },
  {
    title: '学生编号',
    dataIndex: 'studentId',
  },
  {
    title: '权限',
    dataIndex: 'userRole',
    valueType: 'select',
    valueEnum: {
      0: { text: '普通用户', status: 'Default' },
      1: {
        text: '管理员',
        status: 'Success',
      },
    },
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    valueType: 'date',
    sorter: true,
    hideInSearch: true,
  },

  {
    title: '操作',
    valueType: 'option',
    key: 'option',
    render: (text, record, _, action) => [
      <a
          key="editable"
          onClick={() => {
            console.log("编辑触发")
            action?.startEditable?.(record.id);
          }}
      >
        编辑
      </a>,
      <a
        key="delete"
        onClick={() => {
          const userId = record.id; // 获取用户ID
          removeUser({id: userId})
            .then(response => {
              // 处理删除成功的逻辑
              console.log('用户删除成功', response);
              // 刷新
              action?.reload()
            })
            .catch(error => {
              // 处理删除失败的逻辑
              console.error('用户删除失败', error);
              // 可以显示错误信息或执行其他操作

            });
        }}
      >
        删除
      </a>

    ],
  },
];

export default () => {
  const actionRef = useRef<ActionType>();
  return (
      <ProTable<API.CurrentUser>
          columns={columns}
          actionRef={actionRef}
          cardBordered
          request={async (params = {}, sort, filter) => {
            console.log(sort, filter);
            await waitTime(2000);
            const userList = await searchUsers();
            return {
              data: userList,
            }
          }}
          editable={{
            type: 'multiple',
          }}
          columnsState={{
            persistenceKey: 'pro-table-singe-demos',
            persistenceType: 'localStorage',
            onChange(value) {
              console.log('value: ', value);
            },
          }}
          rowKey="id"
          search={{
            labelWidth: 'auto',
          }}
          options={{
            setting: {
              listsHeight: 400,
            },
          }}
          form={{
            // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
            syncToUrl: (values, type) => {
              if (type === 'get') {
                return {
                  ...values,
                  created_at: [values.startTime, values.endTime],
                };
              }
              return values;
            },
          }}
          pagination={{
            pageSize: 5,
            onChange: (page) => console.log(page),
          }}
          dateFormatter="string"
          headerTitle="用户表格"
          toolBarRender={() => [
          ]}
      />
  );
};
