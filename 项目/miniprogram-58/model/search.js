import { getGoodsList } from './goods';

/**
 * @param {number} sort
 * @param {number} pageNum
 * @param {number} pageSize
 * @param {number} minPrice
 * @param {number} maxPrice
 * @param {string} keyword
 */

export function getSearchHistory() {
  return {
    historyWords: [
      '面霜',
      '口红',
      '精华',
      '抗皱',
      '自然堂',
      '美白'
    ],
  };
}

export function getSearchPopular() {
  return {
    popularWords: [
      '面霜',
      '口红',
      '精华',
      '抗皱',
      '自然堂',
      '美白'
    ],
  };
}

export function getSearchResult() {
  return {
    saasId: null,
    storeId: null,
    pageNum: 1,
    pageSize: 30,
    totalCount: 1,
    spuList: getGoodsList(7),
    algId: 0,
  };
}
