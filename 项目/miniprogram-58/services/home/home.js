import { config, cdnBase } from '../../config/index';

/** 获取首页数据 */
function mockFetchHome() {
  const { delay } = require('../_utils/delay');
  const { genSwiperImageList } = require('../../model/swiper');
  return delay().then(() => {
    return {
      swiper: genSwiperImageList(),
      tabList: [
        {
          text: '精选推荐',
          key: 0,
        },
        {
          text: '清洁',
          key: 1,
        },
        {
          text: '保湿补水',
          key: 2,
        },
        {
          text: '抗氧化',
          key: 3,
        },
        {
          text: '防晒',
          key: 4,
        },
        {
          text: '滋养修护',
          key: 5,
        },
        {
          text: '淡化痘印',
          key: 6,
        },
      ],
      activityImg: `${cdnBase}/activity/banner.png`,
    };
  });
}

/** 获取首页数据 */
export function fetchHome() {
  if (config.useMock) {
    return mockFetchHome();
  }
  return new Promise((resolve) => {
    resolve('real api');
  });
}
