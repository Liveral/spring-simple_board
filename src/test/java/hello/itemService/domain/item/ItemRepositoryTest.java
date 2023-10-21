package hello.itemService.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class ItemRepositoryTest {
    ItemRepository itemRepository= new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }
    @Test
    void save(){
        Item item = new Item("itemA", 10000,10);
        Item savedItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId());
        org.assertj.core.api.Assertions.assertThat(findItem).isEqualTo(savedItem);
    }
    @Test
    void findAll(){
        Item item1 = new Item("itemA", 10000,10);
        Item item2 = new Item("itemB", 20000,5);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result=itemRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1,item2);
    }
    @Test
    void updateItem(){
        Item item1 = new Item("itemC", 10000,7);
        Item savedItem = itemRepository.save(item1);
        Long itemId=savedItem.getId();

        Item updateParam = new Item("item2", 20000, 12);
        itemRepository.update(itemId,updateParam);

        Item findItem = itemRepository.findById(itemId);
        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());


    }
}