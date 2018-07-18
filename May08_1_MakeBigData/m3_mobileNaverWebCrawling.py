# _*_ coding:utf-8 _*_
import requests
from bs4 import BeautifulSoup

# html파싱 : bs4모듈 설치

# JSoup

if __name__ == '__main__':
    u = 'https://m.naver.com/'
    res = requests.get(u)
    #print(res.text)
    
    #f = open('C:/dwm/bigData/mobileNaverCrawling.txt', 'a', encoding='utf-8')
    f = open('/home/dwm/myBigData/mobileNaverCrawling.txt', 'a', encoding='utf-8')
    
    # 전체
    html = BeautifulSoup(res.text, 'html.parser')
    
    # html.select('css 선택자')
    news = html.select('a.ut_a')
    
    for n in news:
        nTxt = n.text
        nTxt = nTxt.replace('\n', '')
        print(nTxt)
        f.write(n.text + '\n')
        
    f.close()