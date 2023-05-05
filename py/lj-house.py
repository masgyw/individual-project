# encoding: utf8

from datetime import datetime
import requests
from bs4 import BeautifulSoup
import time
import random
import json
# 提供ua信息的的包
from fake_useragent import UserAgent
import csv

# BASE_DIR = 'e:/Temp/python/'
# cloud server
BASE_DIR = '/opt/data/web-crawler/lian_jia/'

def get_city_url():
    ua = UserAgent(path=r"./user_agent.json")
    url = 'https://www.lianjia.com/city'
    header = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate, br',
        'Accept-Language': 'zh-CN,zh;q=0.9',
        'Connection': 'keep-alive',
        'Cookie': 'lianjia_uuid=07e69a53-d612-4caa-9145-b31c2e9410f4; ',
        'DNT': '1',
        'Host': 'www.lianjia.com',
        'Referer': 'https://www.lianjia.com/',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': ua.random
    }
    index_response = requests.get(url=url, headers=header)
    if index_response.status_code != 200:
        print('connect index False')
    index_soup = BeautifulSoup(index_response.text, 'lxml')
    city_url_dict = {}
    for each_province in index_soup.find_all('div', class_='city_list'):
        for each_city in each_province.find_all('li'):
            city_url_dict[each_city.get_text()] = each_city.find('a')['href']
    return city_url_dict


def get_house_info(city_url, city_name, region):
    ua = UserAgent(path=r"./user_agent.json")
    session0_header = {
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
        'Accept-Encoding': 'gzip, deflate, br',
        'Accept-Language': 'zh-CN,zh;q=0.9',
        'Connection': 'keep-alive',
        'DNT': '1',
        'Host': city_url.split('/')[-2],
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': ua.random
    }

    # 会话构建，首先访问该城市首页url，获取cookies信息
    session0 = requests.session()
    session0.get(url=city_url, headers=session0_header)

    page_url = [city_url, city_url + '/ershoufang/' + region + '/'] + \
        [(city_url + '/ershoufang/' + region + '/pg{}/').format(str(i))
         for i in range(2, 101)]

    all_house_list = []

    for i in range(1, 2):
        # 为每一个页面构建不同的Referer信息
        header = {
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
            'Accept-Encoding': 'gzip, deflate, br',
            'Accept-Language': 'zh-CN,zh;q=0.9',
            'Connection': 'keep-alive',
            'DNT': '1',
            'Host': city_url.split('/')[-2],
            'Referer': page_url[i - 1],
            'Upgrade-Insecure-Requests': '1',
            'User-Agent': ua.random
        }
        index_response = session0.get(url=page_url[i], headers=header)
        #print('page url :' + page_url[i])
        # 有些城市可能没有100页的二手房信息，因此执行完最后一页就需要跳出循环
        # 或者没有成功访问页面，返回的状态码不是200，跳出循环
        if index_response.status_code != 200:
            print(city_name, 'page', str(i), 'pass')
            break
        index_soup = BeautifulSoup(index_response.text, 'lxml')
        try:
            for each_house in index_soup.find_all('li', class_='clear LOGVIEWDATA LOGCLICKDATA'):
                each_house_dict = {
                    'house_code': each_house.find('div', class_='title').find('a')['data-housecode'],
                    'house_url': each_house.find('div', class_='title').find('a')['href'],
                    'house_name': each_house.find('div', class_='title').find('a').get_text(),
                    'house_desc': each_house.find('div', class_='houseInfo').get_text().replace(' ', ''),
                    'xiaoqu_info': each_house.find('div', class_='positionInfo').get_text().replace(' ', ''),
                    # 房屋标签
                    'house_tag': each_house.find('div', class_='tag').get_text('/'),
                    # 总价
                    'house_totalPrice': each_house.find('div', class_='totalPrice').get_text(),
                    # 单价
                    'house_unitPrice': each_house.find('div', class_='unitPrice').get_text(),
                    'city': city_name,
                    'region': region
                }
                all_house_list.append(each_house_dict)
            # print(city_name, region, 'page', str(i), 'done', len(all_house_list))
            # 访问一次随机休眠
            time.sleep(random.randint(1, 3))
        except:
            print(city_name, 'data fetch execption!')
            break
        # 因为发现有些城市可能会没有二手房界面，比如滁州。因此加入一个条件判别，如果没有就跳出循环
        if i > 4 and len(all_house_list) == 0:
            print(city_name, '获取失败')
            break

    return all_house_list

def write_csv_header():
    cur_tm = time.strftime("%Y%m%d_%H%M%S", time.localtime())
    header = ['house_code','house_url','house_name','house_desc','xiaoqu_info','house_tag','house_totalPrice','house_unitPrice','city','region']
    file_path = BASE_DIR + 'lj-house_' + cur_tm + ".csv"
    print('生成数据文件：', file_path)
    with open(file_path, 'a', encoding='utf-8', newline='') as fd:
        writer = csv.DictWriter(fd, header)
        writer.writeheader()
        fd.close
    return file_path

def write_to_text(file_path, house_list):
    header = ['house_code','house_url','house_name','house_desc','xiaoqu_info','house_tag','house_totalPrice','house_unitPrice','city','region']
    with open(file_path, 'a', encoding='utf-8', newline='') as fd:
        writer = csv.DictWriter(fd, header)
        writer.writerows(house_list)
        fd.close

def main():
    # 获取各城市的url
    # city_url_dict = get_city_url()
    # for key, values in city_url_dict.items():
    #     print(key)
    #     print(values)

    city_url = 'https://sh.lianjia.com/'
    city_name = '上海'
    regions = ['yangpu', 'pudong', 'baoshan']
    data_file = write_csv_header()
    for region in regions:
        print('>> 开始获取房屋信息', region)
        house_list = get_house_info(city_url, city_name, region)
        write_to_text(data_file, house_list)
        print('<< 获取房屋信息结束', region)

if __name__ == '__main__':
    main()
