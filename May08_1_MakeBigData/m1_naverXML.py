# _*_ coding:utf-8 _*_
from urllib.parse import quote
import requests
from xml.etree.ElementTree import fromstring

# 서버에 응답 받아오기
# maven같은 시스템 내장되어 있음

# cmd => pip3 install 모듈명
# requests 모듈

# 네이버 지식in 검색
# QHJ9Kaajxipg3xqFIrt6
# 2MCQo3NDZT

def cut(s):
    s = s.replace('<b>', '')
    s = s.replace('</b>', '')
    s = s.replace('&amp;', '')
    s = s.replace('&gt;', '')
    s = s.replace('&lt;', '')
    s = s.replace('&quot;', '')
    s = s.replace('[', '')
    s = s.replace(']', '')
    s = s.replace('(', '')
    s = s.replace(')', '')
    return s

def exect():
    # ㅋ -> %2A; 한글을 utf-8로 바꾸기
    # urllib.parser
    q = quote('박근혜 업적')
    u = "https://openapi.naver.com/v1/search/kin.xml?display=100&query=" + q
    
    # 요청 해더
    #h = {'제목':'값', ...}
    h = {'X-Naver-Client-Id':'QHJ9Kaajxipg3xqFIrt6', 'X-Naver-Client-Secret':'2MCQo3NDZT'}
    
    # 요청해서 응답 받기
    res = requests.get(url=u, headers=h)
    
    # 응답 내용
    print(res.text)
    
    # 파일 열기
    # r : read; w: write, a:append
    #f = open('C:/dwm/bigData/naverIntelIn.txt', 'a', encoding='utf-8')
    f = open('/home/dwm/myBigData/naverIntelIn.txt', 'a', encoding='utf-8')
    
    # 전체 
    xml = fromstring(res.text)
    
    # <item>들
    items = xml.getiterator('item')
    
    t, d, li = None, None, None
    for item in items:
        t = cut(item.find('title').text)
        d = cut(item.find('description').text)
        li = cut(item.find('link').text)
        # 파이썬은 \r\n 안해도됨
        txt = "%s,\t%s,\t%s\n" % (t, d, li)
        print(txt, end='\n\n')
        
        # 쓰기
        f.write(txt)
    
    
    # 닫기
    f.close()
    
if __name__ == '__main__':
    exect();